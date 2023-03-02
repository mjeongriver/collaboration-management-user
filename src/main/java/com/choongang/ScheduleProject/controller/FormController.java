package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forms")
public class FormController {

	@GetMapping("/editors")
	public String editors() {
		return "/forms/forms-editors";
	}
	@GetMapping("/elements")
	public String elements() {
		return "/forms/forms-elements";
	}
	@GetMapping("/layouts")
	public String layouts() {
		return "/forms/forms-layouts";
	}
	@GetMapping("/validation")
	public String validation() {
		return "/forms/forms-validation";
	}
	
}
