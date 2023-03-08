package com.choongang.scheduleproject.project.service;

import org.apache.ibatis.annotations.Mapper;

import com.choongang.scheduleproject.command.ProjectVO;

@Mapper
public interface ProjectMapper {
	public int regist(ProjectVO vo);
}
