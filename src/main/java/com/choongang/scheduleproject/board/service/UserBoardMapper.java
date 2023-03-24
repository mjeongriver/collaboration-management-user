package com.choongang.scheduleproject.board.service;

import java.util.ArrayList;
import java.util.Map;

import com.choongang.scheduleproject.command.FileVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserBoardVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserBoardMapper {
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
	public int getContent(Map<String, Object> map); //글 등록
	
	
	/***
	 * 
	 * @param pj_num
	 * @return ProjectVO
	 */
	public ArrayList<ProjectVO> getObserver(int pj_num); //옵저버
	
	/***
	 * 
	 * @param FileVO
	 * @return int
	 */
	public int fileUploadList(FileVO vo); //파일 업로드
	
	/***
	 * 
	 * @param pj_num
	 * @param board_num
	 * @return UserBoardVO
	 */
	public UserBoardVO detailContent(@Param("pjNum") int pj_num, @Param("boardNum") int board_num);
	
	/***
	 * 
	 * @param board_num
	 * @return FileVO
	 */
	public ArrayList<FileVO> fileList(int board_num);
}
