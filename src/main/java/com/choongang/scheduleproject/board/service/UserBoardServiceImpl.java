package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

@Service("userBoardService")
public class UserBoardServiceImpl implements UserBoardService{
	
	@Autowired
	private UserBoardMapper userBoardMapper;

	@Override
	public ArrayList<UserBoardVO> getList(UserBoardVO vo) {
		return userBoardMapper.getList(vo);
	}

	@Override
	public int getTotal(Criteria cri) {
		return 0;
	}

	@Override
	public int getCount(Criteria cri) {
		return 0;
	}

	
}
