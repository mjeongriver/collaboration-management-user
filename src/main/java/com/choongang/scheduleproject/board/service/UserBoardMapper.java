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
	public ArrayList<ProjectVO> getObserver(int pj_num); //옵저버 얻어와서 옵저버일 경우 글 작성 못하게 막음
	
	/***
	 * 
	 * @param FileVO
	 * @return int
	 */
	public int fileUploadList(FileVO vo); //글 등록 시 파일 업로드
	
	/***
	 * 
	 * @param pj_num
	 * @param board_num
	 * @return UserBoardVO
	 */
	public UserBoardVO detailContent(@Param("pjNum") int pj_num, @Param("boardNum") int board_num); //게시글 상세 보기
	
	/***
	 * 
	 * @param board_num
	 * @return FileVO
	 */
	public ArrayList<FileVO> fileList(int board_num); //상세 화면에서 파일 정보 불러 오기
	
	/***
	 * 
	 * @param board_num
	 * @return int
	 */
	public int deleteContent(int board_num); //게시글 삭제 
	
	/***
	 * 
	 * @param map
	 * @return int
	 */
	public int updateContent(Map<String, Object> map); //게시글 수정
	
	/***
	 * 
	 * @param fileVO
	 * @return int
	 */
	public int updateFileList(FileVO fileVO); //파일 업로드 수정(기존 데이터 dcheck 1로 변경)
	
}
