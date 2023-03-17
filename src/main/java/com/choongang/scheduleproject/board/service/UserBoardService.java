package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;
import java.util.List;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

public interface UserBoardService {

	/***
	 * 
	 * @param cri
	 * @param pj_num
	 * @return
	 */
	public ArrayList<UserBoardVO> getList(Criteria cri, int pj_num); //페이지

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
	public int getCount(Criteria cri, int pj_num); //검색 결과 건 수


}
