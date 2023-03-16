package com.choongang.scheduleproject.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.choongang.scheduleproject.command.ChatVO;
import com.choongang.scheduleproject.project.service.ProjectService;
import com.mysql.cj.Session;

@RestController
public class ChatAjaxController {
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;

	@GetMapping("/setChat")
	public int setChat(@RequestParam("chat_content") String chat_content,
					   @RequestParam("pj_num") int pj_num,HttpSession session) {
		System.out.println(pj_num);
		ChatVO vo = new ChatVO();
		String user_id = (String)session.getAttribute("user_id");
		vo.setChat_content(chat_content);
		//세션아이디로 이름불러오기 해야함.
		String user_name = projectService.getUserName(user_id);
		vo.setChat_writer(user_name);
		vo.setPj_num(pj_num);
		
		return projectService.setChat(vo);
	}
	
	@GetMapping("/getChat")
	public ArrayList<ChatVO> getChat(@RequestParam("pj_num") int pj_num){
			
		
		return projectService.getChat(pj_num);
	}
	
	@GetMapping("/deleteChat")
	public int deleteChat(@RequestParam("chat_num") int chat_num){
			
		
		return projectService.deleteChat(chat_num);
	}
	
	@GetMapping("/modifyChat")
	public int modifyChat(@RequestParam("chat_num") int chat_num,
						  @RequestParam("modifyContent") String modifyContent){
			
		
		return projectService.modifyChat(chat_num,modifyContent);
	}
	
}
