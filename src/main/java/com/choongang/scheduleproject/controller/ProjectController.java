package com.choongang.scheduleproject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.project.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;

	//프로젝트 생성
	@GetMapping("/projectAdd")
	public String projectAdd() {
		return "/project/projectAdd";
	}
	
	@GetMapping("/projectStarted")
	public String projectStarted() {		
		return "/project/projectStarted";
	}
	
	
	//전체 진척률 보기
	@GetMapping("/projectUserTeamChart")
	public String projectUserTeamChart() {
		return "/project/projectUserTeamChart";
	}
	
	@GetMapping("/projectUserMyChart")
	public String projectUserMyChart() {
		return "/project/projectUserMyChart";
	}
	
	@GetMapping("/projectCalendar")
	public String projectCalendar() {
		return "/project/projectCalendar";
	}
	
	//등록 요청
	@PostMapping("/registForm")
	public String registForm(ProjectVO vo, RedirectAttributes ra) {
		int result = projectService.regist(vo);
		String msg = result == 1 ? "정상 입력 되었습니다." : "등록에 실패하였습니다";
		ra.addFlashAttribute("msg", msg);
		System.out.println(result);
		System.out.println(vo.toString());
		return "redirect:/project/projectStarted"; 
	}
	
}
