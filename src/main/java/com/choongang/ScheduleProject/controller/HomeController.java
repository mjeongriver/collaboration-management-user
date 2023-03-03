package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index() {
			System.out.println(21311);
		return "projectList";
	}
	
	
	

}
