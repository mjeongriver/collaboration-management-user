package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

import org.apache.ibatis.annotations.Mapper;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

@Mapper
public interface UserBoardMapper {
	public ArrayList<UserBoardVO> getList(UserBoardVO vo); //페이지
	public int getTotal(Criteria cri); //전체 게시글 수
	public int getCount(Criteria cri); //검색 결과 건 수
}
