package com.choongang.scheduleproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PageController {

	@GetMapping("/login")
	public String login() {
		return "/pages/pages-login";
	}
	@GetMapping("/register")
	public String register() {
		return "/pages/pages-register";
	}
	@GetMapping("/statistics")
	public String statistics() {
		return "/pages/pages-statistics";
	}
}
