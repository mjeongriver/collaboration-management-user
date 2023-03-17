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

	//프로젝트 등록
	@Override
	public int regist(ProjectVO vo) {
		return projectMapper.regist(vo);
	}

	//프로젝트 별 팀원 등록
	@Override
	public int registMember(ProjectMemberVO pvo) {
		return projectMapper.registMember(pvo);
	}

	//부서 목록
	@Override
	public List<ProjectVO> getDepList() {
		return projectMapper.getDepList();
	}

	//부서별 팀원 목록
	@Override
	public List<ProjectVO> getDepMemberList(int department_id) {
		return projectMapper.getDepMemberList(department_id);
	}

	//startProjectList
	@Override
	public ArrayList<UserVO> getProjectMember(int pj_num) {

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
	public ArrayList<ChatVO> getChat(int pj_num) {

		return projectMapper.getChat(pj_num);
	}

	@Override
	public int deleteChat(int chat_num) {

		return projectMapper.deleteChat(chat_num);
	}

	@Override
	public int modifyChat(int chat_num,String modify_content) {

		return projectMapper.modifyChat(chat_num,modify_content);
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
	public ProjectVO getProject(int pj_num) {

		return projectMapper.getProject(pj_num);
	}

	@Override
	public String getUserName(String user_id) {

		return projectMapper.getUserName(user_id);
	}

	@Override
	public int deleteProject(int pj_num) {

		return projectMapper.deleteProject(pj_num);
	}






}
