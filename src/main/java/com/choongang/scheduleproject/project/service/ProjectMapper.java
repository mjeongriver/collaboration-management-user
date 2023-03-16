package com.choongang.scheduleproject.project.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.choongang.scheduleproject.command.ProjectMemberVO;
import com.choongang.scheduleproject.command.ChatVO;

import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserVO;

@Mapper
public interface ProjectMapper {
	
	//프로젝트 생성
	public int regist(ProjectVO vo);
	
	//프로젝트 별 팀원 등록
	public int registMember(ProjectMemberVO pvo);
	
	//부서 목록 
	public List<ProjectVO> getDepList();
	
	//부서별 팀원 목록
	public List<ProjectVO> getDepMemberList(int department_id);

	//startProjectList
	//프로젝트멤버 받아옴
	public ArrayList<UserVO> getProjectMember(int pj_num);
	//프로젝트정보 받아옴
	public ProjectVO getProject(int pj_num);
	//사용안함
	public UserVO getUserVO(String user_id);
	//채팅 내용 입력
	public int setChat(ChatVO vo);
	//채팅 정보 받아옴
	public ArrayList<ChatVO> getChat(int pj_num);
	//채팅 삭제
	public int deleteChat(int chat_num);
	//채팅수정
	public int modifyChat(@Param("chat_num") int chat_num, @Param("modifyContent") String modifyContent);
	
	public ArrayList<ProjectVO> getProjectList(@Param("user_id") String user_id);
	
	public int changeBookmark(@Param("user_id") String user_id,@Param("pj_num") int pj_num,@Param("pj_bookmark") boolean pj_bookmark);
	
	public String getUserName(String user_id);
}
