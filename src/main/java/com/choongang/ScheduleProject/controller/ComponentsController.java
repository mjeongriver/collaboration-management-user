package com.choongang.ScheduleProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/components")
public class ComponentsController {
	
	@GetMapping("/accordion")
	public String accordion() {
		return "/components/components-accordion";
	}
	@GetMapping("/alerts")
	public String alerts() {
		return "/components/components-alerts";
	}
	@GetMapping("/badges")
	public String badges() {
		return "/components/components-badges";
	}
	@GetMapping("/breadcrumbs")
	public String breadcrumbs() {
		return "/components/components-breadcrumbs";
	}
	@GetMapping("/buttons")
	public String buttons() {
		return "/components/components-buttons";
	}
	@GetMapping("/cards")
	public String cards() {
		return "/components/components-cards";
	}
	@GetMapping("/carousel")
	public String carousel() {
		return "/components/components-carousel";
	}
	@GetMapping("/listGroup")
	public String listGroup() {
		return "/components/components-list-group";
	}
	@GetMapping("/modal")
	public String modal() {
		return "/components/components-modal";
	}
	@GetMapping("/pagination")
	public String pagination() {
		return "/components/components-pagination";
	}
	@GetMapping("/progress")
	public String progress() {
		return "/components/components-progress";
	}
	@GetMapping("/spinners")
	public String spinners() {
		return "/components/components-spinners";
	}
	@GetMapping("/tabs")
	public String tabs() {
		return "/components/components-tabs";
	}
	@GetMapping("/tooltips")
	public String tooltips() {
		return "/components/components-tooltips";
	}
	@GetMapping("/chat")
	public String chat() {
		return "/components/mainchat";
	}
}
