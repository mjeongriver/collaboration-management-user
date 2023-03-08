package com.choongang.scheduleproject.project.service;

import java.util.ArrayList;
import java.util.List;

import com.choongang.scheduleproject.command.ChatVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserVO;

public interface ProjectService {
	
	public int regist(ProjectVO vo);
	
	public ArrayList<UserVO> getProjectMember(String pj_num);
	
	public UserVO getUserVO(String user_id);
	
	public int setChat(ChatVO vo);
	
	public ArrayList<ChatVO> getChat(String pj_num);
	
	public int deleteChat(int chat_num);
	
	public int modifyChat(int chat_num, String modifyContent);
	
}
