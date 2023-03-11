package com.choongang.scheduleproject.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choongang.scheduleproject.command.ProjectVO;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public int regist(ProjectVO vo) {
		return projectMapper.regist(vo);
	}

	@Override
	public List<ProjectVO> getDepList() {
		return projectMapper.getDepList();
	}

	@Override
	public List<ProjectVO> getDepMemberList(int department_id) {
		return projectMapper.getDepMemberList(department_id);
	}
	
	
	
	
	
	
	
	
	

}
