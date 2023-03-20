package com.choongang.scheduleproject.impl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.choongang.scheduleproject.board.service.UserBoardMapper;
import com.choongang.scheduleproject.board.service.UserBoardService;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserBoardVO;

import org.springframework.beans.factory.annotation.Autowired;


@Service("userBoardService")
public class UserBoardServiceImpl implements UserBoardService{
	
	@Autowired
	private UserBoardMapper userBoardMapper;

	@Override
	public ArrayList<UserBoardVO> getList(Map<String, Object> map) {
		return userBoardMapper.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return userBoardMapper.getCount(map);
	}

	@Override
	public int getContent(Map<String, Object> map) {
		return userBoardMapper.getContent(map);
	}

	@Override
	public ArrayList<ProjectVO> getObserver(int pj_num) {
		return userBoardMapper.getObserver(pj_num);
	}
	
	
	
	

	
}
