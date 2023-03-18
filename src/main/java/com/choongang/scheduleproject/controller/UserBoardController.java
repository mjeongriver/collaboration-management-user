package com.choongang.scheduleproject.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.scheduleproject.board.service.AdminNoticeService;
import com.choongang.scheduleproject.board.service.UserBoardService;
import com.choongang.scheduleproject.command.AdminNoticeListVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.project.service.ProjectService;
import com.choongang.scheduleproject.util.Criteria;
import com.choongang.scheduleproject.util.PageVO;

@Controller
@RequestMapping("/userboards")
public class UserBoardController {

	@Autowired
	@Qualifier("adminNoticeService")
	private AdminNoticeService adminNoticeService;

	@Autowired
	@Qualifier("userBoardService")
	private UserBoardService userBoardService;

	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;


	@GetMapping("/board-list")
	public String boardList(Criteria cri, Model model,
								@RequestParam("pj_num") int pj_num) {

		//getProject
		ProjectVO pjVO = projectService.getProject(pj_num);
		model.addAttribute("pjVO", pjVO);
		System.out.println("pjVO: " + pjVO);
		
		System.out.println("cri : " + cri);
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pjNum", pj_num);
		map.put("cri", cri);
	
		
		//토탈 검색(search에 따른 검색결과 건수 변화 위해 cri를 매개변수로 사용함)
		int total = userBoardService.getCount(map);
		model.addAttribute("count", total); //검색결과 건수
		
		System.out.println("total: " + total);
		
		List<UserBoardVO> list = userBoardService.getList(map); //페이지에 넘길 데이터를 모델에 담는다.
		model.addAttribute("boardList", list);	
		System.out.println("list" + list);
		
		PageVO pageVO = new PageVO(cri, total); //pageVO 객체에서 사용할 criteria 와 total 값 주입 
		model.addAttribute("pageVO", pageVO); //넘겨줄 VO 데이터
		System.out.println("page : " + pageVO.toString());
		model.addAttribute("pjNum", pj_num);
		
		return "/userboards/board-list";
	}

	@GetMapping("/board-regist")
	public String boardRegist() {
		return "/userboards/board-regist";
	}

	@GetMapping("/board-modify")
	public String boardModify() {
		return "/userboards/board-modify";
	}

	@GetMapping("/board-content")
	public String boardContent() {
		return "/userboards/board-content";
	}

	@GetMapping("/notice-list")
	public String noticeTableList(Criteria cri, Model model) {

		int total = adminNoticeService.getCount(cri);
		model.addAttribute("AdminNoticeList", adminNoticeService.getList(cri)); //페이지에 넘길 데이터

		PageVO pageVO = new PageVO(cri, total); //페이징에 사용
		model.addAttribute("pageVO", pageVO);


		return "/userboards/notice-list";
	}

	//위의 noticeTableList를 상세 조회하는 컨트롤러
	@GetMapping("/notice-content")
	public String noticeContent(@RequestParam("noticeNum") int noticeNum, Model model) {

		//클릭한 글 번호에 대한 내용을 조회
		AdminNoticeListVO adminNoticeListVO = adminNoticeService.getContent(noticeNum);
		model.addAttribute("adminNoticeListVO", adminNoticeListVO);


		return "/userboards/notice-content";
	}



}
