package com.choongang.scheduleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/userLogin")
	public String userLogin() {
		return "/user/userLogin";
	}
	
	@GetMapping("/userMypage")
	public String userMypage() {
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
	
	
	
}



