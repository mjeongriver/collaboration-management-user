package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

	@GetMapping("/enterpasswordprofile")
	public String enterpasswordprofile() {
		return "/users/users-enterpasswordprofile";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "/users/users-profile";
	}
}
