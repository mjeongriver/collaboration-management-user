package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;
import java.util.Map;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface UserBoardMapper {
	
	/***
	 * 
	 * @param vo
	 * @return List
	 */
	public ArrayList<UserBoardVO> getList(Map<String, Object> map); //페이지
	
	
	/***
	 * 
	 * @param cri
	 * @return int
	 */
	public int getCount(Map<String, Object> map); //검색 결과 건 수
}
