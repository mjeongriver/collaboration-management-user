package com.choongang.scheduleproject.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.choongang.scheduleproject.board.service.UserBoardMapper;
import com.choongang.scheduleproject.board.service.UserBoardService;
import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

import org.springframework.beans.factory.annotation.Autowired;


@Service("userBoardService")
public class UserBoardServiceImpl implements UserBoardService{
	
	@Autowired
	private UserBoardMapper userBoardMapper;

	@Override
	public ArrayList<UserBoardVO> getList(Criteria cri, int pj_num) {
		return userBoardMapper.getList(cri, pj_num);
	}

	@Override
	public int getTotal(Criteria cri) {
		return userBoardMapper.getTotal(cri);
	}

	@Override
	public int getCount(Criteria cri, int pj_num) {
		return userBoardMapper.getCount(cri, pj_num);
	}

	
}
