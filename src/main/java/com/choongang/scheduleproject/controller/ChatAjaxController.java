package com.choongang.scheduleproject.controller;

import java.util.ArrayList;
import java.util.Map;

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

@RestController
public class ChatAjaxController {
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;

	@PostMapping("/setChat")
	public int setChat(@RequestParam("chat_content") String chat_content) {

		ChatVO vo = new ChatVO();
		
		vo.setChat_content(chat_content);
		//세션아이디로 이름불러오기 해야함.
		vo.setChat_writer("test1");
		vo.setPj_num(1);
		
		return projectService.setChat(vo);
	}
	
	@GetMapping("/getChat")
	public ArrayList<ChatVO> getChat(){
			
		
		return projectService.getChat("1");
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
