package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tables")
public class TableController {
	
	@GetMapping("/data")
	public String data() {
		return "/tables/noticeTableList";
	}
	@GetMapping("/general")
	public String general() {
		return "/tables/teamTableList";
	}
	@GetMapping("/regist")
	public String regist() {
		return "tables/tableRegist";
	}
	@GetMapping("/modify")
	public String modify() {
		return "tables/tableModify";
	}
	@GetMapping("/content")
	public String content() {
		return "tables/tableContent";
	}
	@GetMapping("/noticecontent")
	public String noticeContent() {
		return "tables/noticeContent";
	}
	@GetMapping("/noticeregist")
	public String noticeRegist() {
		return "tables/noticeRegist";
	}
	@GetMapping("/noticemodify")
	public String noticeModify() {
		return "tables/noticeModify";
	}
}
