package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

public interface UserBoardService {

	/***
	 *
	 * @param vo
	 * @return ArrayList
	 */
	public ArrayList<UserBoardVO> getList(UserBoardVO vo); //페이지

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
	public int getCount(Criteria cri); //검색 결과 건 수


}
