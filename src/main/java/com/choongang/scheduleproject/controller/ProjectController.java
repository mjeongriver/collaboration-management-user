package com.choongang.scheduleproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserVO;
import com.choongang.scheduleproject.project.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;

	//프로젝트 생성
	@GetMapping("/project-add")
	public String projectAdd() {
		return "/project/project-add";
	}

	@GetMapping("/project-started")
	public String projectStarted(Model model,@RequestParam("pj_num") int pj_num) {

		//채팅화면에 멤버 정보를 받아옴
		ArrayList<UserVO> list = new ArrayList<>();
		list = projectService.getProjectMember(pj_num);

		ProjectVO pjVO = projectService.getProject(pj_num);
		
		model.addAttribute("pjVO",pjVO);
		model.addAttribute("list",list);


		return "/project/project-started";
	}


	//전체 진척률 보기
	@GetMapping("/project-user-team-chart")
	public String projectUserTeamChart(Model model,@RequestParam("pj_num") int pj_num) {
		ProjectVO pjVO = projectService.getProject(pj_num);

		model.addAttribute("pjVO",pjVO);
		return "/project/project-user-team-chart";
	}

	@GetMapping("/project-user-my-chart")
	public String projectUserMyChart(Model model,@RequestParam("pj_num") int pj_num) {
		ProjectVO pjVO = projectService.getProject(pj_num);

		model.addAttribute("pjVO",pjVO);
		return "/project/project-user-my-chart";
	}

	@GetMapping("/project-calendar")
	public String projectCalendar(Model model,@RequestParam("pj_num") int pj_num) {

		ProjectVO pjVO = projectService.getProject(pj_num);

		model.addAttribute("pjVO",pjVO);

		return "/project/project-calendar";
	}

	@PostMapping("/regist-form")
	public String registForm(ProjectVO vo, RedirectAttributes ra) {
		int result = projectService.regist(vo);
		String msg = result == 1 ? "정상 입력 되었습니다." : "등록에 실패하였습니다";
		ra.addFlashAttribute("msg", msg);
		System.out.println(result);
		System.out.println(vo.getPjName());
		System.out.println(vo.toString());
		return "../";
	}

}
