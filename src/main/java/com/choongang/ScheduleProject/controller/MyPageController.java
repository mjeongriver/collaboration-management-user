package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypages")
public class MyPageController {

	@GetMapping("/confirmMypage")
	public String confirmMypage() {
		return "/mypages/confirmMypage";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "/mypages/mypage";
	}
}
