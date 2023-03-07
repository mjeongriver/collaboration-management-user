package com.choongang.scheduleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PageController {

	@GetMapping("/login")
	public String login() {
		return "/pages/pagesLogin";
	}
	@GetMapping("/register")
	public String register() {
		return "/pages/pagesRegister";
	}
	@GetMapping("/statistics")
	public String statistics() {
		return "/pages/pagesStatistics";
	}
}
