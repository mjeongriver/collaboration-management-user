package com.choongang.scheduleproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

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
	public String index(Model model,HttpSession session) {
		String user_id = (String)session.getAttribute("user_id");
		System.out.println(user_id);
		ArrayList<ProjectVO> list = new ArrayList<>();
		list = projectService.getProjectList(user_id);
		
		model.addAttribute("list",list);
		
		return "user/userStartProjectList";

	}
	

	@GetMapping("/2")
	public String index2(Model model,HttpSession session) {
		String user_id = (String)session.getAttribute("user_id");
		ArrayList<ProjectVO> list = new ArrayList<>();
		list = projectService.getProjectList(user_id);
		
		model.addAttribute("list",list);
		
		return "user/userStartProjectList2";

	}
	
	@GetMapping("/3")
	public String index3(Model model,HttpSession session) {
		String user_id = (String)session.getAttribute("user_id");
		ArrayList<ProjectVO> list = new ArrayList<>();
		list = projectService.getProjectList(user_id);
		
		model.addAttribute("list",list);
		
		return "user/userStartProjectList3";

	}
	
	

}
