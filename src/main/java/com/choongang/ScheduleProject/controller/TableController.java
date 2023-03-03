package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tables")
public class TableController {
	
	@GetMapping("/data")
	public String data() {
		return "/tables/tables-data";
	}
	
	@GetMapping("/general")
	public String general() {
		return "/tables/tables-general";
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
