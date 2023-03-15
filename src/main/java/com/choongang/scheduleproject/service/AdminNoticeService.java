package com.choongang.scheduleproject.service;

import java.util.ArrayList;

import com.choongang.scheduleproject.command.AdminNoticeListVO;
import com.choongang.scheduleproject.util.Criteria;

public interface AdminNoticeService {
	
	public ArrayList<AdminNoticeListVO> getList(Criteria cri); //페이지
	public int getTotal(Criteria cri); //전체 게시글수
	public int getCount(Criteria cri); //검색 결과건수
	
	public AdminNoticeListVO getContent(int notice_num); //상세조회
	

}
