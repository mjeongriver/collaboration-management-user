package com.choongang.scheduleproject.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.scheduleproject.command.UserVO;
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


	@GetMapping("/user-login")
	public String userLogin() {		
		return "/user/user-login";
	}

	@GetMapping("/user-mypage")
	public String userMypage(HttpSession session, Model model) {
		//세션값을 기반으로 DB에서 정보 조회
		String user_id = (String)session.getAttribute("user_id");
		UserVO vo = userService.getMyPageInfo(user_id);
		model.addAttribute("vo", vo);
		return "/user/user-mypage";
	}

	@GetMapping("/user-register")
	public String userRegister() {
		return "/user/user-register";
	}

	@GetMapping("/user-start-project-list")
	public String userStartProjectList() {
		return "/user/user-start-project-list";
	}

	@GetMapping("/user-confirm-mypage")
	public String userConfirmMypage() {
		return "/user/user-confirm-mypage";
	}

	@GetMapping("/user-find-id")
	public String userFindId() {
		return "/user/user-find-id";
	}

	@GetMapping("/user-reset-pw")
	public String userResetPw() {
		return "/user/user-reset-pw";
	}

	@GetMapping("/user-email-error")
	public String userEmailError(RedirectAttributes ra) {
		//메시지 담아서 리다이렉트
		String msg = "이메일 전송 시도 중 문제가 발생했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/user-login";
	}

	//회원가입 요청
	@PostMapping("/register-form")
	public String register(@Valid UserVO vo, Errors errors, RedirectAttributes ra) {
		
		System.out.println(vo);
		
		//서버단에서 유효성 검사 실행
		if(errors.hasErrors()) {
			//메시지 담아서 리다이렉트
			String msg = "회원가입 시도 중 서버에서 문제가 발생했습니다. 관리자에게 문의하세요.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/user-register";
		}
		//비밀번호 암호화 작업
		String encodedPassword = passwordEncoder.encode(vo.getUserPw());
		vo.setUserPw(encodedPassword);
		//현재 시간을 회원가입일 user_regdate에다가 저장
		LocalDateTime nowTime = LocalDateTime.now();
		vo.setUserRegdate(nowTime);
		//DB에 저장 시도
		int result = userService.register(vo);
		//메시지 담아서 리다이렉트
		String msg = result == 1 ? "회원가입에 성공하였습니다." : "회원가입에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/user-login"; //로그인화면으로		
	}

	//로그인 요청
	@PostMapping("/login")
	public String login(@Valid UserVO vo, Errors errors, RedirectAttributes ra, HttpSession session, Model model) {
		//서버단에서 유효성 검사 실행
		if(errors.hasErrors()) {
			//메시지 담아서 리다이렉트
			String msg = "로그인 시도 중 서버에서 문제가 발생했습니다. 관리자에게 문의하세요.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/user-login";
		}
		//DB에서 아이디로 검색해서 조회해옴
		UserVO result = userService.login(vo);
		if(result == null) { //일치하는 아이디가 없음
			//메시지 담아서 리다이렉트
			String msg = "아이디가 일치하지 않습니다.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/user-login";
		} else { // 일치하는 아이디가 있음
			//암호화된 비밀번호와 일치하는지 확인
			String msg = "";
			if(!passwordEncoder.matches(vo.getUserPw(), result.getUserPw())) { //비밀번호 다름 - 로그인 실패
				//실패횟수 기록
				result.setUserLoginfail(result.getUserLoginfail()+1);
				userService.failCount(result); //실패횟수 기록
				//유저정보 다시 가져오기
				UserVO result2 = userService.login(result);
				//실패횟수 5회 이상일 시 비활성화
				if(result2.getUserLoginfail() > 4) { 
					//계정 잠그기
					userService.lockAccount(result2);
					//메시지 담아서 리다이렉트
					msg = "계정이 비활성화되었습니다. 비밀번호 초기화를 진행해주세요.";
					ra.addFlashAttribute("msg", msg);
					return "redirect:/user/user-reset-pw";
				} else { //실패횟수를 alert에다가 실어 redirect로 보내기
					msg = "로그인을 " + result2.getUserLoginfail() + "번 실패했습니다. 5회 틀리면 계정이 비활성화됩니다.";
					ra.addFlashAttribute("msg", msg);
					return "redirect:/user/user-login";
				}
			} else { // 비밀번호 같음 - 로그인
				//계정이 비활성화되어있는지 확인
				if(result.getUserActive() == 0) { // 계정이 비활성화되어있음
					//로그인실패
					//메시지 담아서 리다이렉트
					msg = "비활성화된 계정입니다. 비밀번호 초기화를 진행해주세요.";
					ra.addFlashAttribute("msg", msg);
					return "redirect:/user/user-reset-pw";
				} else { //계정이 활성화되어있음
					//로그인 성공: 실패횟수 0으로 변경
					userService.failReset(result);
					//user_log에 log기록 추가하기
					//현재 시간을 회원가입일 user_regdate에다가 저장
					LocalDateTime nowTime = LocalDateTime.now();
					result.setUserRegdate(nowTime);
					userService.insertLog(result);
					//모델에 DB정보를 담아서 화면에 뿌려줌
					model.addAttribute("vo", result);
					//세션 부여
					session.setAttribute("user_name", result.getUserName());
					session.setAttribute("user_id", result.getUserId());
					session.setAttribute("user_img", result.getUserImg());
					session.setAttribute("user_role", result.getUserRole());
					return "user/user-start-project-list";					
				}

			}
		}
	}

	//아이디 찾기
	@PostMapping("/find-id")
	public String findId(UserVO vo, RedirectAttributes ra) {
		//서버에서의 유효성 검사를 어떻게 처리해줘야 할지 모르겠습니다. 상황에 따라 필요한 값만 NotNull로 처리할 수는 없는건지요? -> Valid 어노테이션을 사용하지 않고 처리했습니다.
		UserVO result = userService.findId(vo);
		if(result == null) { //일치하는 사람이 없음
			//메시지 담아서 리다이렉트
			String msg = "회원정보와 일치하는 아이디가 없습니다.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/user-login";
		} else { // 일치하는 아이디가 있음
			//메시지 담아서 리다이렉트
			String id = result.getUserId();
			String msg = vo.getUserName() + "님의 아이디는 "+ id.substring(0, id.length()-3) + "***입니다.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/user-login";
		}	
	}

	//비밀번호 초기화
	@PostMapping("/reset-pw")
	public String resetPw(UserVO vo, RedirectAttributes ra) {
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
		vo.setUserPw(encodedPassword);
		//얘도 이메일 검증을 거치는 작업을 추후에 추가하면 좋을 것 같음. => Ajax로 처리함
		int result = userService.resetPw(vo);
		//실패횟수 초기화하기
		userService.failReset(vo);
		//비활성화 풀어주기
		userService.unlockAccount(vo);
		//메시지 담아서 리다이렉트
		String msg = result == 1 ? "초기화된 비밀번호는 "+ newPw +"! 입니다. 마이페이지에서 비밀번호를 수정해주시기 바랍니다." : "비밀번호 초기화에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/user-login";
	}

	//카카오 로그인
	@GetMapping("/kakao")
	public String kakao(@RequestParam("code") String code, RedirectAttributes ra, HttpSession session, Model model) {
		String token = kakao.getAccessToken(code);//인가코드 code를 가지고 token을 발급받기
		Map<String, Object> map = kakao.getUserInfo(token);//어세스토큰 token을 가지고 사용자데이터를 가져오기
		//DB에서 조회해서 로그인처리
		UserVO vo = new UserVO();
		vo.setUserEmail((String)map.get("email")); //카카오에서 가져온 이메일값으로 로그인 시도
		UserVO result = userService.kakaoLogin(vo);

		if(result == null) { //일치하는 아이디가 없음
			// 회원가입 페이지로 넘겨줘야 함
			//메시지 담아서 리다이렉트
			String msg = "이메일과 일치하는 데이터가 존재하지 않습니다. 다시 확인해주세요.";
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/user-login";
		} else { // 일치하는 아이디가 있음
			//모델에 DB정보를 담아서 화면에 뿌려줌
			model.addAttribute("vo", result);
			//세션 부여
			session.setAttribute("user_name", result.getUserName());
			session.setAttribute("user_id", result.getUserId());
			session.setAttribute("user_img", result.getUserImg());
			session.setAttribute("user_role", result.getUserRole());
			return "user/user-start-project-list";			
		}
	}

	//마이페이지에서 개인정보 변경
	@PostMapping("/change-info")
	public String changeInfo(UserVO vo, RedirectAttributes ra, HttpSession session) {
		int result = userService.changeInfo(vo);
		//메시지 담아서 리다이렉트
		String msg = result == 1 ? "회원정보 수정에 성공하였습니다." : "회원정보 수정에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/user-mypage"; //마이페이지화면으로	
	}

	//마이페이지에서 비밀번호 변경
	@PostMapping("/change-pw")
	public String changePw(UserVO vo, RedirectAttributes ra, HttpSession session) {
		//비밀번호 암호화 작업
		String encodedPassword = passwordEncoder.encode(vo.getUserPw());
		vo.setUserPw(encodedPassword);
		//DB에 암호화된 비밀번호 저장
		int result = userService.changePw(vo);
		//메시지 담아서 리다이렉트
		String msg = result == 1 ? "비밀번호 수정에 성공하였습니다. 다시 로그인해주세요." : "비밀번호 수정에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		session.invalidate(); // 세션 만료
		return "redirect:/user/user-login"; //로그인화면으로	
	}

	//마이페이지에서 이미지 삭제
	@GetMapping("/remove-img")
	public String removeImg(HttpSession session, RedirectAttributes ra) {
		//세션으로 id가져옴
		String user_id = (String)session.getAttribute("user_id");
		//DB에서 이미지 URL 삭제
		int result = userService.removeImg(user_id);
		//메시지 담아서 리다이렉트
		String msg = result == 1 ? "이미지 삭제에 성공하였습니다." : "이미지 삭제에 실패했습니다. 관리자에게 문의하세요.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/user-mypage"; //로그인화면으로	
	}


	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes ra) {
		session.invalidate(); // 세션 만료시키기
		String msg = "로그아웃되었습니다.";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/user/user-login"; //로그인화면으로	

	}


}



