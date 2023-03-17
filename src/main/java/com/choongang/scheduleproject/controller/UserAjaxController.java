package com.choongang.scheduleproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choongang.scheduleproject.command.DepartmentVO;
import com.choongang.scheduleproject.command.EmailVO;
import com.choongang.scheduleproject.command.UserVO;
import com.choongang.scheduleproject.impl.MailServiceImpl;
import com.choongang.scheduleproject.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j // log를 띄우기 위해 사용하는 롬복 - 현재는 주석처리해서 사용되지 않음
public class UserAjaxController {

	@Autowired
	private UserService userService;

	@Autowired
	//네이버 메일
	private MailServiceImpl mailService;

	// 아이디 중복 확인
	@GetMapping("check-all-id")
	public String checkAllId(@RequestParam("userId") String userId) {
		return userService.checkAllId(userId);
	}

	// 이메일 중복 확인
	@GetMapping("check-all-email")
	public String checkAllEmail(@RequestParam("userEmail") String userEmail) {
		return userService.checkAllEmail(userEmail);
	}

	// 모든 부서 가져오기
	@GetMapping("get-all-department")
	public List<DepartmentVO> getAllDepartment() {
		return userService.getAllDepartment();
	}

	// 회원가입 시 인증코드가 담긴 메일 발송하기 (제한시간 3분)
	@PostMapping("send-mail")
	public String sendMail(@RequestParam("userEmail") String userEmail, @RequestParam("joinResetFind") String joinResetFind) throws Exception {
		String code = mailService.sendSimpleMessage(userEmail, joinResetFind);
		//log.info("인증코드 : " + code);
		return code;
	}

	// 이메일 인증하기
	@GetMapping("verify-mail")
	public EmailVO verifyMail(@RequestParam("userEmail") String userEmail, @RequestParam("joinResetFind") String joinResetFind) {
		EmailVO vo = new EmailVO();
		vo.setEmail(userEmail);
		vo.setJoinResetFind(joinResetFind); // 회원가입 요청, 비밀번호 초기화 요청, 아이디 찾기 요청인지 구분하기 위함
		return userService.verifyMail(vo);
	}

	// 아이디 이메일 일치 여부
	@GetMapping("check-id-and-email")
	public UserVO checkIdAndEmail(@RequestParam("userId") String userId, @RequestParam("userEmail") String userEmail) {
		UserVO vo = new UserVO();
		vo.setUserId(userId);
		vo.setUserEmail(userEmail);
		return userService.checkIdAndEmail(vo);
	}

	// 이름 이메일 일치 여부
	@GetMapping("check-name-and-email")
	public UserVO checkNameAndEmail(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail) {
		UserVO vo = new UserVO();
		vo.setUserName(userName);
		vo.setUserEmail(userEmail);
		return userService.checkNameAndEmail(vo);
	}


}
