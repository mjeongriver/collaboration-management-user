package com.choongang.scheduleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userboards")
public class UserBoardController {
	
	
	@GetMapping("/teamBoardList")
	public String teamBoardList() {
		return "/userboards/teamBoardList";
	}
	@GetMapping("/teamBoardRegist")
	public String teamBoardRegist() {
		return "/userboards/teamBoardRegist";
	}
	@GetMapping("/teamBoardModify")
	public String teamBoardModify() {
		return "/userboards/teamBoardModify";
	}
	@GetMapping("/teamBoardContent")
	public String teamBoardContent() {
		return "/userboards/teamBoardContent";
	}
	
	
	@GetMapping("/noticeTableList")
	public String noticeTableList() {
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
	
	
}
