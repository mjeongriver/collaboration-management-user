package com.choongang.scheduleproject.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.choongang.scheduleproject.command.DepartmentVO;
import com.choongang.scheduleproject.command.EmailVO;
import com.choongang.scheduleproject.command.UserVO;

@Mapper
public interface UserMapper {
	public int register(UserVO vo); // 회원가입 요청
	public String checkAllId(String user_id); // 아이디 중복 확인
	public String checkAllEmail(String user_email); // 이메일 중복 확인
	public List<DepartmentVO> getAllDepartment(); // 모든 부서 가져오기
	public UserVO login(UserVO vo); // 로그인 요청
	public UserVO findId(UserVO vo); // 아이디 찾기
	public int resetPw(UserVO vo); // 비밀번호 초기화하기
	public UserVO kakaoLogin(UserVO vo); // 카카오 로그인 요청
	public void failCount(UserVO vo); // 로그인 실패 시 실패기록 카운트
	public void lockAccount(UserVO vo); // 계정 비활성화
	public void unlockAccount(UserVO vo); // 계정 활성화
	public void failReset(UserVO vo); //로그인 성공 시 실패횟수 0으로 바꾸기
	public void insertLog(UserVO vo); // 로그인 성공 시 로그에 기록하기
	public int sendVerifyCode(EmailVO vo); // 이메일인증요청을 보내면 만료시간과 인증코드를 DB에 저장 / EmailMapper를 따로 만들어야 하나요?
	public EmailVO verifyMail(EmailVO vo); // 이메일과 인증코드로 인증하기
	public UserVO checkIdAndEmail(UserVO vo); // 아이디와 이메일 일치여부 확인하기
}
