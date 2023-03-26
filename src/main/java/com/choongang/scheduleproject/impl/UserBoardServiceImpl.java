package com.choongang.scheduleproject.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.choongang.scheduleproject.board.service.UserBoardMapper;
import com.choongang.scheduleproject.board.service.UserBoardService;
import com.choongang.scheduleproject.command.FileVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserBoardVO;

import org.springframework.beans.factory.annotation.Autowired;

@Service("userBoardService")
public class UserBoardServiceImpl implements UserBoardService{
	@Autowired
	private UserBoardMapper userBoardMapper;

	@Override
	public ArrayList<UserBoardVO> getList(Map<String, Object> map) {
		return userBoardMapper.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		return userBoardMapper.getCount(map);
	}

	@Override
	public int getContent(UserBoardVO vo) {
		return userBoardMapper.getContent(vo);
	}

	@Override
	public ArrayList<ProjectVO> getObserver(int pj_num) {
		return userBoardMapper.getObserver(pj_num);
	}

	//여기서 파일 업로드 반복 처리
	@Override
	public int fileUploadList(List<FileVO> fvoList) {
		int result = 0;
		for(FileVO vo: fvoList) {
			result = userBoardMapper.fileUploadList(vo);
		}
		return result;
	}

	@Override
	public UserBoardVO detailContent(int pj_num, int board_num) {
		return userBoardMapper.detailContent(pj_num, board_num);
	}

	@Override
	public ArrayList<FileVO> fileList(int board_num) {
		return userBoardMapper.fileList(board_num);
	}

	@Override
	public int deleteContent(int board_num) {
		return userBoardMapper.deleteContent(board_num);
	}

	@Override
	public int updateContent(Map<String, Object> map) {
		return userBoardMapper.updateContent(map);
	}

	@Override
	public int updateFileList(FileVO fileVO) {
		return userBoardMapper.updateFileList(fileVO);
	}

}
