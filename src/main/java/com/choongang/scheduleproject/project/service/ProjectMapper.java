package com.choongang.scheduleproject.project.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.choongang.scheduleproject.command.ChatVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserVO;

@Mapper
public interface ProjectMapper {
	public int regist(ProjectVO vo);
	
	public ArrayList<UserVO> getProjectMember(String pj_num);
	
	public UserVO getUserVO(String user_id);
	
	public int setChat(ChatVO vo);
	
	public ArrayList<ChatVO> getChat(String pj_num);
	
	public int deleteChat(int chat_num);
	
	public int modifyChat(@Param("chat_num") int chat_num, @Param("modifyContent") String modifyContent);
	
	
	/////////////////startProjectList////////////////////
	
	
}
