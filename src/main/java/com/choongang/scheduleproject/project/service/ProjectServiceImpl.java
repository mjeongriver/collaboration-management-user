package com.choongang.scheduleproject.project.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.choongang.scheduleproject.command.ProjectMemberVO;
import com.choongang.scheduleproject.command.ChatVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserVO;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public int regist(ProjectVO vo) {
		return projectMapper.regist(vo);
	}
	
	@Override
	public int registMember(ProjectMemberVO pvo) {
		return projectMapper.registMember(pvo);
	}


	@Override
	public List<ProjectVO> getDepList() {
		return projectMapper.getDepList();
	}

	@Override
	public List<ProjectVO> getDepMemberList(int department_id) {
		return projectMapper.getDepMemberList(department_id);
	}
	
	
	
	
	
	
	
	
	

	@Override
	public ArrayList<UserVO> getProjectMember(String pj_num) {
		
		return projectMapper.getProjectMember(pj_num);
	}

	@Override
	public UserVO getUserVO(String user_id) {
		
		return projectMapper.getUserVO(user_id);
	}

	@Override
	public int setChat(ChatVO vo) {
		
		return projectMapper.setChat(vo);
	}

	@Override
	public ArrayList<ChatVO> getChat(String pj_num) {
		
		return projectMapper.getChat(pj_num);
	}

	@Override
	public int deleteChat(int chat_num) {
		
		return projectMapper.deleteChat(chat_num);
	}

	@Override
	public int modifyChat(int chat_num,String modifyContent) {

		return projectMapper.modifyChat(chat_num,modifyContent);
	}

	@Override
	public ArrayList<ProjectVO> getProjectList(String user_id) {
	
		return projectMapper.getProjectList(user_id);
	}

	@Override
	public int changeBookmark(String user_id, int pj_num, boolean pj_bookmark) {
		
		return projectMapper.changeBookmark(user_id, pj_num, pj_bookmark);
	}

	@Override
	public ProjectVO getProject(String pj_num) {
		
		return projectMapper.getProject(pj_num);
	}



	


}
