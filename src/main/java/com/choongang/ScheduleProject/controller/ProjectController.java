package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

	//로그인 후 첫 진입 화면
	@GetMapping("/projectstart")
	public String projectstart() {
		return "project/projectstart";
	}
	
	//전체 진척률 보기
	@GetMapping("/userchart1")
	public String userchart1() {
		return "project/userchart1";
	}
	
	@GetMapping("/userchart2")
	public String userchart2() {
		return "project/userchart2";
	}
	
	@GetMapping("/calendar")
	public String calendar() {
		return "project/calendar";
	}
	
	
}
