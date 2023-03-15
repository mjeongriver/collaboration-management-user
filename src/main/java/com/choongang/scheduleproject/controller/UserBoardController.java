package com.choongang.scheduleproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.choongang.scheduleproject.command.AdminNoticeListVO;
import com.choongang.scheduleproject.userboard.service.AdminNoticeService;
import com.choongang.scheduleproject.userboard.service.UserBoardService;
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
	
	
	@GetMapping("/teamBoardList")
	public String teamBoardList(Criteria cri, Model model) {
		int total = userBoardService.getCount(cri);
		
		return "/userboards/teamBoardList";
	}
	@GetMapping("/teamBoardRegist")
	public String teamBoardRegist() {
		return "/userboards/teamBoardRegist";
	}
	@GetMapping("/teamBoardModify")
	public String teamBoardModify() {
		return "/userboards/teamBoardModify";
	}
	@GetMapping("/teamBoardContent")
	public String teamBoardContent() {
		return "/userboards/teamBoardContent";
	}
	
	@GetMapping("/noticeTableList")
	public String noticeTableList(Criteria cri, Model model) {
		
		int total = adminNoticeService.getCount(cri);
		model.addAttribute("count", total); //토탈 검색
		model.addAttribute("AdminNoticeList", adminNoticeService.getList(cri)); //페이지에 넘길 데이터
		
		PageVO pageVO = new PageVO(cri, total); //페이징에 사용
		System.out.println("페이지"+pageVO);
		model.addAttribute("pageVO", pageVO);
		
		
		return "/userboards/noticeTableList";
	}
	@GetMapping("/noticeContent")
	public String noticeContent() {
		return "/userboards/noticeContent";
	}
	@GetMapping("/noticeRegist")
	public String noticeRegist() {
		return "/userboards/noticeRegist";
	}
	@GetMapping("/noticeModify")
	public String noticeModify() {
		return "/userboards/noticeModify";
	}
	
	
}
