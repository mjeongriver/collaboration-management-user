package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.choongang.scheduleproject.command.FileVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserBoardVO;

public interface UserBoardService {

	/***
	 * 
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList<UserBoardVO> getList(Map<String, Object> map); //페이지


	/***
	 * 
	 * @param map
	 * @return int
	 */
	public int getCount(Map<String, Object> map); //검색 결과 건 수
	
	/***
	 * 
	 * @param map
	 * @return int
	 */
	public int getContent(Map<String, Object> map); //글 등록 내용
	
	/***
	 * 
	 * @param fvoList
	 * @return int
	 */
	public int fileUploadList(List<FileVO> fvoList);
	
	/***
	 * 
	 * @param pj_num
	 * @return ArrayList
	 */
	public ArrayList<ProjectVO> getObserver(int pj_num); //옵저버 멤버 


}
