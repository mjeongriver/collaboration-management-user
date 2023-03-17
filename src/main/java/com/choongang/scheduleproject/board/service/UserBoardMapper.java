package com.choongang.scheduleproject.board.service;

import java.util.List;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;


@Mapper
public interface UserBoardMapper {
	
	/***
	 * 
	 * @param vo
	 * @return List
	 */
	public List<UserBoardVO> getList(Criteria cri, @Param("pj_num") int pj_num); //페이지
	
	/***
	 * 
	 * @param cri
	 * @return int
	 */
	public int getTotal(Criteria cri); //전체 게시글 수
	
	/***
	 * 
	 * @param cri
	 * @return int
	 */
	public int getCount(Criteria cri, @Param("pj_num") int pj_num); //검색 결과 건 수
}
