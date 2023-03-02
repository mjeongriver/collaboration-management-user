package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/icons")
public class IconController {
	
	@GetMapping("/bootstrap")
	public String bootstrap() {
		return "/icons/icons-bootstrap";
	}
	@GetMapping("/boxicons")
	public String boxicons() {
		return "/icons/icons-boxicons";
	}
	@GetMapping("/remix")
	public String remix() {
		return "/icons/icons-remix";
	}
}
