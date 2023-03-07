package com.choongang.scheduleproject.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

	//프로젝트 생성
	@GetMapping("/projectAdd")
	public String projectAdd() {
		return "/project/projectAdd";
	}
	
	@GetMapping("/projectStarted")
	public String projectStarted() {		
		return "/project/projectStarted";
	}
	
//	//로그인 후 첫 진입 화면
//	@GetMapping("/projectList")
//	public String projectList() {
//		return "/project/projectList";
//	}
	
	//전체 진척률 보기
	@GetMapping("/projectUserTeamChart")
	public String projectUserTeamChart() {
		return "/project/projectUserTeamChart";
	}
	
	@GetMapping("/projectUserMyChart")
	public String projectUserMyChart() {
		return "/project/projectUserMyChart";
	}
	
	@GetMapping("/projectCalendar")
	public String projectCalendar() {
		return "/project/projectCalendar";
	}
	
}
