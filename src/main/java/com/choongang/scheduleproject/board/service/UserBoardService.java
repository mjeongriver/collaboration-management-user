package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;
import java.util.Map;

import com.choongang.scheduleproject.command.UserBoardVO;
import com.choongang.scheduleproject.util.Criteria;

public interface UserBoardService {

	/***
	 * 
	 * @param cri
	 * @param pj_num
	 * @return ArrayList
	 */
	public ArrayList<UserBoardVO> getList(Map<String, Object> map); //페이지


	/***
	 *
	 * @param cri
	 * @return int
	 */
	public int getCount(Map<String, Object> map); //검색 결과 건 수
	
	/***
	 * 
	 * @param vo
	 * @return int
	 */
	public int getContent(Map<String, Object> map);


}
