package com.choongang.scheduleproject.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.scheduleproject.command.UserVO;
import com.choongang.scheduleproject.mail.service.MailService;
import com.choongang.scheduleproject.user.service.UserService;
import com.choongang.scheduleproject.util.KakaoAPI;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	//비밀번호 암호화
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private KakaoAPI kakao;
	
	
	@GetMapping("/userLogin")
	public String userLogin() {		
		return "/user/userLogin";
	}
	
	@GetMapping("/userMypage")
	public String userMypage(HttpSession session, Model model) {
		String user_id = (String)session.getAttribute("user_id");
		UserVO vo = userService.getMyPageInfo(user_id);
		model.addAttribute("vo", vo);
		
		return "/user/userMypage";
	}
	
	@GetMapping("/userRegister")
	public String userRegister() {
		return "/user/userRegister";
	}
	
	@GetMapping("/userStartProjectList")
	public String userStartProjectList() {
		return "/user/userStartProjectList";
	}
	
	@GetMapping("/userConfirmMypage")
	public String userConfirmMypage() {
		return "/user/userConfirmMypage";
	}
	
	@GetMapping("/userFindId")
	public String userFindId() {
		return "/user/userFindId";
	}
	
	@GetMapping("/userResetPw")
	public String userResetPw() {
		return "/user/userResetPw";
	}
	
	@GetMapping("/userEmailError")
	public String userEmailError(RedirectAttributes ra) {
    	String msg = "이메일 전송 시도 중 문제가 발생했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/userLogin";
	}
	
	//회원가입 요청
	@PostMapping("/RegisterForm")
	public String register(@Valid UserVO vo, Errors errors, RedirectAttributes ra) {
		//서버단에서 유효성 검사 실행
		if(errors.hasErrors()) {
			System.out.println(errors.toString());
			String msg = "회원가입 시도 중 서버에서 문제가 발생했습니다. 관리자에게 문의하세요.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/userRegister";
		}
		
		//비밀번호 암호화 작업
		String encodedPassword = passwordEncoder.encode(vo.getUser_pw());
		vo.setUser_pw(encodedPassword);
		
		//현재 시간을 회원가입일 user_regdate에다가 저장
		LocalDateTime nowTime = LocalDateTime.now();
		vo.setUser_regdate(nowTime);
		
		int result = userService.register(vo);
		String msg = result == 1 ? "회원가입에 성공하였습니다." : "회원가입에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/userLogin"; //로그인화면으로		
	}
	
	//로그인 요청
	@PostMapping("/Login")
	public String login(@Valid UserVO vo, Errors errors, RedirectAttributes ra, HttpSession session, Model model) {
		
		//서버단에서 유효성 검사 실행
		if(errors.hasErrors()) {
			System.out.println(errors.toString());
			String msg = "로그인 시도 중 서버에서 문제가 발생했습니다. 관리자에게 문의하세요.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/userLogin";
		}
		
		UserVO result = userService.login(vo);
		
		if(result == null) { //일치하는 아이디가 없음
			String msg = "아이디가 일치하지 않습니다.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/userLogin";
		} else { // 일치하는 아이디가 있음
			//암호화된 비밀번호와 일치하는지 확인
			String msg = "";
			if(!passwordEncoder.matches(vo.getUser_pw(), result.getUser_pw())) { //비밀번호 다름 - 로그인 실패
				//실패횟수 기록
				result.setUser_loginfail(result.getUser_loginfail()+1);
				userService.failCount(result); //실패횟수 기록
				
				//유저정보 다시 가져오기
				UserVO result2 = userService.login(result);
				
				//실패횟수 5회 이상일 시 비활성화
				if(result2.getUser_loginfail() > 4) { 
					//계정 잠그기
					userService.lockAccount(result2);
					
					msg = "계정이 비활성화되었습니다. 비밀번호 초기화를 진행해주세요.";
					ra.addFlashAttribute("msg", msg);
					return "redirect:/user/userResetPw";
				} else { //실패횟수를 alert에다가 실어 redirect로 보내기
					msg = "로그인을 " + result2.getUser_loginfail() + "번 실패했습니다. 5회 틀리면 계정이 비활성화됩니다.";
					ra.addFlashAttribute("msg", msg);
					return "redirect:/user/userLogin";
				}
			} else { // 비밀번호 같음 - 로그인
				//계정이 비활성화되어있는지 확인
				if(result.getUser_active() == 0) {
					//비활성화되어있으면 로그인 실패처리해주기
					msg = "비활성화된 계정입니다. 비밀번호 초기화를 진행해주세요.";
					ra.addFlashAttribute("msg", msg);
					return "redirect:/user/userResetPw";
				} else {
					//로그인 성공 시 실패횟수 0으로 변경
					userService.failReset(result);
					//user_log에 log기록 추가하기
					//현재 시간을 회원가입일 user_regdate에다가 저장
					LocalDateTime nowTime = LocalDateTime.now();
					result.setUser_regdate(nowTime);
					userService.insertLog(result);

					model.addAttribute("vo", result);

					session.setAttribute("user_name", result.getUser_name()); //로그인 성공 시 세션 부여
					session.setAttribute("user_id", result.getUser_id()); //로그인 성공 시 세션 부여
					session.setAttribute("user_img", result.getUser_img()); //로그인 성공 시 세션 부여
					session.setAttribute("user_role", result.getUser_role()); //로그인 성공 시 세션 부여
					
					return "user/userStartProjectList";					
				}
				
			}
		}
	}
	
	//아이디 찾기
	@PostMapping("/FindId")
	public String findId(UserVO vo, RedirectAttributes ra) {
		//서버에서의 유효성 검사를 어떻게 처리해줘야 할지 모르겠습니다. 상황에 따라 필요한 값만 NotNull로 처리할 수는 없는건지요?		
		UserVO result = userService.findId(vo);
				
		if(result == null) { //일치하는 사람이 없음
			String msg = "회원정보와 일치하는 아이디가 없습니다.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/userLogin";
		} else { // 일치하는 아이디가 있음
			String id = result.getUser_id();
			String msg = vo.getUser_name() + "님의 아이디는 "+ id.substring(0, id.length()-3) + "***입니다.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/userLogin";
		}	
	}
	
	//비밀번호 초기화
	@PostMapping("/ResetPw")
	public String resetPw(UserVO vo, RedirectAttributes ra) {
		
		System.out.println(vo);		
		
		//랜덤 비밀번호 생성
        Random random = new Random(); 
        int length = 10;

        StringBuffer newPw = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int choice = random.nextInt(3);
            switch(choice) {
                case 0:
                    newPw.append((char)((int)random.nextInt(25)+97));
                    break;
                case 1:
                    newPw.append((char)((int)random.nextInt(25)+65));
                    break;
                case 2:
                    newPw.append((char)((int)random.nextInt(10)+48));
                    break;
                default:
                    break;
            }
        }
        
	    //비밀번호 암호화 작업
		String encodedPassword = passwordEncoder.encode(newPw + "!");
		vo.setUser_pw(encodedPassword);
		
		//얘도 이메일 검증을 거치는 작업을 추후에 추가하면 좋을 것 같음. => Ajax로 처리함
		int result = userService.resetPw(vo);
		
		//실패횟수 초기화하기
		userService.failReset(vo);
		
		//비활성화 풀어주기
		userService.unlockAccount(vo);

		
		String msg = result == 1 ? "초기화된 비밀번호는 "+ newPw +"! 입니다. 마이페이지에서 비밀번호를 수정해주시기 바랍니다." : "비밀번호 초기화에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/userLogin";
	}
	
	//카카오 로그인
	@GetMapping("/kakao")
	public String kakao(@RequestParam("code") String code, RedirectAttributes ra, HttpSession session) {
		
//		System.out.println("인가코드:" + code);
		String token = kakao.getAccessToken(code);
		
//		System.out.println("어세스토큰:" + token);
		Map<String, Object> map = kakao.getUserInfo(token);
		
//		System.out.println("사용자데이터" + map.toString());
				
		//DB에서 조회해서 로그인처리
		UserVO vo = new UserVO();
		vo.setUser_email((String)map.get("email")); //카카오에서 가져온 이메일값으로 로그인 시도
				
		UserVO result = userService.kakaoLogin(vo);
				
		if(result == null) { //일치하는 아이디가 없음
			// 회원가입 페이지로 넘겨줘야 함
			
			String msg = "이메일과 일치하는 데이터가 존재하지 않습니다. 다시 확인해주세요.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/userLogin";
		} else { // 일치하는 아이디가 있음
			session.setAttribute("user_name", result.getUser_name()); //로그인 성공 시 세션 부여
			session.setAttribute("user_id", result.getUser_id()); //로그인 성공 시 세션 부여
			session.setAttribute("user_img", result.getUser_img()); //로그인 성공 시 세션 부여
			session.setAttribute("user_role", result.getUser_role()); //로그인 성공 시 세션 부여
			return "user/userStartProjectList";			
		}
	}
	
	//마이페이지에서 개인정보 변경
	@PostMapping("/ChangeInfo")
	public String changeInfo(UserVO vo, RedirectAttributes ra, HttpSession session) {
		int result = userService.changeInfo(vo);
		
		String msg = result == 1 ? "회원정보 수정에 성공하였습니다." : "회원정보 수정에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/userMypage"; //마이페이지화면으로	
	}
	
	//마이페이지에서 비밀번호 변경
	@PostMapping("/ChangePw")
	public String changePw(UserVO vo, RedirectAttributes ra, HttpSession session) {
		
	    //비밀번호 암호화 작업
		String encodedPassword = passwordEncoder.encode(vo.getUser_pw());
		vo.setUser_pw(encodedPassword);
		
		int result = userService.changePw(vo);
		
		String msg = result == 1 ? "비밀번호 수정에 성공하였습니다. 다시 로그인해주세요." : "비밀번호 수정에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		
		session.invalidate(); // 세션 만료시키기
		
		return "redirect:/user/userLogin"; //로그인화면으로	
	}
	
	//마이페이지에서 이미지 삭제
	@GetMapping("/RemoveImg")
	public String removeImg(HttpSession session, RedirectAttributes ra) {
		String user_id = (String)session.getAttribute("user_id");
		
		int result = userService.removeImg(user_id);
		
		String msg = result == 1 ? "이미지 삭제에 성공하였습니다." : "이미지 삭제에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/user/userMypage"; //로그인화면으로	
	}
	
	
	//로그아웃
	@GetMapping("/Logout")
	public String logout(HttpSession session, RedirectAttributes ra) {
		session.invalidate(); // 세션 만료시키기
		String msg = "로그아웃되었습니다.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/userLogin"; //로그인화면으로	

	}
	
	
}



