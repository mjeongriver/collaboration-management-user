package com.choongang.scheduleproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.project.service.ProjectService;

@Controller
public class HomeController {
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;
	
	@GetMapping("/")
	public String index(Model model) {

		ArrayList<ProjectVO> list = new ArrayList<>();
		list = projectService.getProjectList("test13");
		
		model.addAttribute("list",list);
		
		return "user/userStartProjectList";

	}
	
	@GetMapping("/2")
	public String index2(Model model) {

		ArrayList<ProjectVO> list = new ArrayList<>();
		list = projectService.getProjectList("test13");
		
		model.addAttribute("list",list);
		
		return "user/userStartProjectList2";

	}
	
	
	

}
