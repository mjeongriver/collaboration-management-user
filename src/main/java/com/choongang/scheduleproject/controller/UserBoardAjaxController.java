package com.choongang.scheduleproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.scheduleproject.board.service.UserBoardService;
import com.choongang.scheduleproject.command.UserBoardVO;

@RestController
public class UserBoardAjaxController {
	
	@Autowired 
	private UserBoardService userBoardService;
	private UserBoardVO vo;
	
	//등록 요청
	@PostMapping("/reg-board")
	@ResponseBody
	public Map<String, Object> regist(@RequestBody Map<String, Object> map,								
									  RedirectAttributes ra) {
		
		String msg = "";
		int result = 0;
		
		UserBoardVO vo = new UserBoardVO();
		vo.setPjNum((String)map.get("pjNum"));	
		vo.setBoardCategory((String)map.get("selectedCategory"));
		vo.setBoardProcess((String)map.get("selectedProcess"));
		vo.setBoardWriter((String)map.get("writer"));
		vo.setBoardWriterId((String)map.get("writerId"));
		vo.setBoardTitle((String)map.get("boardTitle"));
		vo.setBoardStartdate((String)map.get("startDate"));
		vo.setBoardContent((String)map.get("description"));
		vo.setBoardEnddate((String)map.get("endDate"));
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("vo", vo);
		result = userBoardService.getContent(resultMap);
		System.out.println("vo" + vo.toString());
		
		if(!(result == 0)) {
			msg = "등록에 성공 하였습니다.";
		}else {
			msg = "등록에 실패 하였습니다.";
		}

		resultMap.put("msg", msg);
		
		return resultMap;
	}
}
