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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pjNum", pj_num);
		map.put("cri", cri);
	
		int total = userBoardService.getCount(map); //토탈 검색(search에 따른 검색결과 건수 변화 위해 cri를 매개변수로 사용함)
		model.addAttribute("count", total); //검색결과 건수
		
		List<UserBoardVO> list = userBoardService.getList(map); //페이지에 넘길 데이터를 모델에 담는다.
		model.addAttribute("boardList", list);	
		
		PageVO pageVO = new PageVO(cri, total); //pageVO 객체에서 사용할 criteria 와 total 값 주입 
		model.addAttribute("pageVO", pageVO); //넘겨줄 VO 데이터
		model.addAttribute("pjNum", pj_num);
		
		return "/userboards/board-list";
	}

	@GetMapping("/board-regist")
	public String boardRegist() {
		return "/userboards/board-regist";
	}
	
	//registForm 등록 요청
//	@PostMapping("/board-regist-form")
//	public String boardRegistForm(UserBoardVO vo, RedirectAttributes ra,
//								@RequestParam(name = "pj_num", required = true) int pj_num) {
//		//int result = userBoardService.getContent(map);
//		String msg = result == 1 ? "정상 입력 되었습니다." : "등록에 실패하였습니다";
//		ra.addFlashAttribute("msg", msg);
//		System.out.println(result);
//		System.out.println(vo.toString());
//		return "redirect:/userboards/board-list?pj_num=" + pj_num;
////		return "/userboards/board-list";
//	}

	@GetMapping("/board-modify")
	public String boardModify() {
		return "/userboards/board-modify";
	}

	@GetMapping("/board-content")
	public String boardContent() {
		return "/userboards/board-content";
	}

	//여기서부터 user notice 
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
