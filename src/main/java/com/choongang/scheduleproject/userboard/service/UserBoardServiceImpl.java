package com.choongang.scheduleproject.userboard.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

@Service("userBoardService")
public class UserBoardServiceImpl implements UserBoardService{

	@Override
	public ArrayList<UserBoardVO> getList(Criteria cri) {
		return null;
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
