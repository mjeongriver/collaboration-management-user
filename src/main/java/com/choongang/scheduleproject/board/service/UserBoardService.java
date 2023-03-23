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
	 * @return UserBoardVO
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
	public int fileUploadList(List<FileVO> fvoList); //파일 업로드
	
	/***
	 * 
	 * @param pj_num
	 * @return ProjectVO
	 */
	public ArrayList<ProjectVO> getObserver(int pj_num); //옵저버 멤버 
	
	/***
	 * 
	 * @param pj_num
	 * @param board_num
	 * @return UserBoardVO
	 */
	public UserBoardVO detailContent(int pj_num, int board_num); //글 상세 내용 가져오기
	
	/***
	 * 
	 * @param board_num
	 * @return FileVO
	 */
	public ArrayList<FileVO> fileList(int board_num);


}
