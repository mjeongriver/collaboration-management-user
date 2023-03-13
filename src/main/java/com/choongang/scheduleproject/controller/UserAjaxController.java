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
import com.choongang.scheduleproject.mail.service.MailService;
import com.choongang.scheduleproject.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserAjaxController {

	@Autowired
	private UserService userService;
	
	@Autowired
	//네이버 메일
	private MailService mailService;
	
	// 아이디 중복 확인
	@GetMapping("checkAllId")
	public String checkAllId(@RequestParam("user_id") String user_id) {
		return userService.checkAllId(user_id);
	}
	
	// 이메일 중복 확인
	@GetMapping("checkAllEmail")
	public String checkAllEmail(@RequestParam("user_email") String user_email) {
		return userService.checkAllEmail(user_email);
	}
	
	// 모든 부서 가져오기
	@GetMapping("getAllDepartment")
	public List<DepartmentVO> getAllDepartment() {
		return userService.getAllDepartment();
	}
	
	// 회원가입 시 인증코드가 담긴 메일 발송하기 (제한시간 3분)
	@PostMapping("sendMail")
	public String sendMail(@RequestParam("user_email") String user_email, @RequestParam("joinReset") String joinReset) throws Exception {
		String code = mailService.sendSimpleMessage(user_email, joinReset);
        log.info("인증코드 : " + code);
        return code;
	}
	
	// 이메일 인증하기
	@GetMapping("verifyMail")
	public EmailVO verifyMail(@RequestParam("user_email") String user_email, @RequestParam("joinReset") String joinReset) {
		EmailVO vo = new EmailVO();
		vo.setEmail(user_email);
		vo.setJoinReset(joinReset); // 회원가입 요청인지, 비밀번호 초기화 요청인지 구분하기 위함
		
		return userService.verifyMail(vo);
		
	}
	
	// 아이디 이메일 일치 여부
	@GetMapping("checkIdAndEmail")
	public UserVO checkIdAndEmail(@RequestParam("user_id") String user_id, @RequestParam("user_email") String user_email) {
		UserVO vo = new UserVO();
		vo.setUser_id(user_id);
		vo.setUser_email(user_email);
		
		return userService.checkIdAndEmail(vo);
		
	}
	
}
