package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userboards")
public class UserBoardController {
	
	
	@GetMapping("/teamBoardList")
	public String general() {
		return "/userboards/teamBoardList";
	}
	@GetMapping("/teamBoardRegist")
	public String regist() {
		return "/userboards/teamBoardRegist";
	}
	@GetMapping("/teamBoardModify")
	public String modify() {
		return "/userboards/teamBoardModify";
	}
	@GetMapping("/teamBoardContent")
	public String content() {
		return "/userboards/teamBoardContent";
	}
	
	
	@GetMapping("/noticeTableList")
	public String data() {
		return "/userboards/noticeTableList";
	}
	@GetMapping("/noticeContent")
	public String noticeContent() {
		return "/userboards/noticeContent";
	}
	@GetMapping("/noticeRegist")
	public String noticeRegist() {
		return "/userboards/noticeRegist";
	}
	@GetMapping("/noticeModify")
	public String noticeModify() {
		return "/userboards/noticeModify";
	}
	
	@GetMapping("/managermembershiplist")
	public String managermembershiplist() {
		return "/tables/tables-managermembershiplist";
	}
	
	@GetMapping("/projectlist")
	public String projectlist() {
		return "/tables/tables-projectlist";
	}
	
	@GetMapping("/projectlist2")
	public String projectlist2() {
		return "/tables/tables-projectlist2";
	}
	
}
