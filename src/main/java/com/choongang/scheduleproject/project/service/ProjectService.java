package com.choongang.scheduleproject.project.service;

import java.util.List;

import com.choongang.scheduleproject.command.ProjectMemberVO;
import com.choongang.scheduleproject.command.ProjectVO;



public interface ProjectService {
	
	//프로젝트 등록
	public int regist(ProjectVO vo);
	
	//프로젝트 별 팀원 등록
	public int registMember(ProjectMemberVO pvo);
	
	//부서 목록
	public List<ProjectVO> getDepList();
	
	//부서별 팀원 목록
	public List<ProjectVO> getDepMemberList(int department_id);

}