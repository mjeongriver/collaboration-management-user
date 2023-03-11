package com.choongang.scheduleproject.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choongang.scheduleproject.command.ProjectVO;

@Mapper
public interface ProjectMapper {
	
	//프로젝트 생성
	public int regist(ProjectVO vo);
	
	//부서 목록 
	public List<ProjectVO> getDepList();
	
	//부서별 팀원 목록
	public List<ProjectVO> getDepMemberList(int department_id);
	
}
