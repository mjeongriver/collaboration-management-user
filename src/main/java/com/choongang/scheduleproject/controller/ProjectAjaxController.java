package com.choongang.scheduleproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.project.service.ProjectService;



@RestController
public class ProjectAjaxController {

	@Autowired
	private ProjectService projectService;
	
	private ProjectVO vo;
	
	//부서 요청
	@GetMapping("/getDepList") 
	public List<ProjectVO> getDepList (){
		return projectService.getDepList();
	}
	
	//부서별 팀원 요청
	@GetMapping("/getDepMemberList")
	public List<ProjectVO> getDepMemberList(@RequestParam("department_id") int department_id) {
//		ProjectVO vo = ProjectVO.builder()
//								.department_id(department_id)
//								.build();
		System.out.println(projectService.getDepMemberList(department_id).toString());

		return projectService.getDepMemberList(department_id);
	}
	
	
	
	
}
