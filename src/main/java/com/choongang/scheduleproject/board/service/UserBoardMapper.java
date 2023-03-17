package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

public interface UserBoardMapper {
	public ArrayList<UserBoardVO> getList(Criteria cri); //페이지
	public int getTotal(Criteria cri); //전체 게시글 수
	public int getCount(Criteria cri); //검색 결과 건 수
}
