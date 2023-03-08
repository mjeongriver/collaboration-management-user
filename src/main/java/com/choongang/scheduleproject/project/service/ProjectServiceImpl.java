package com.choongang.scheduleproject.project.service;

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

}
