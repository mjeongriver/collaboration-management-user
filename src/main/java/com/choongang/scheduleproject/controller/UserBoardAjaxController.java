package com.choongang.scheduleproject.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.choongang.scheduleproject.board.service.UserBoardService;
import com.choongang.scheduleproject.command.FileVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.command.UserBoardVO;

@RestController
public class UserBoardAjaxController {
	
	@Autowired 
	private UserBoardService userBoardService;
	private UserBoardVO vo;
	
	@Autowired
	private AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	//업로드 패스
	@Value("${project.uploadpath}")
	private String uploadpath;
	
	//등록 요청
	@PostMapping("/reg-board")
	@ResponseBody
	public int regist(MultipartHttpServletRequest multipart, HttpSession session) throws IllegalStateException, IOException {
		String msg = "";
		int result = 0;
		int result2 = 0;
		
		String categorySelect =  multipart.getParameter("categorySelect");
		String processSelect = multipart.getParameter("processSelect");
		String boardTitle = multipart.getParameter("boardTitle");
		String writer = (String)session.getAttribute("user_id");
		String startDate = multipart.getParameter("startDate");
		String endDate = multipart.getParameter("endDate");
		String description = multipart.getParameter("description");
		
		//multipart는 string 값으로 넘어오기 때문에 int로 형변환
		String pjNum = multipart.getParameter("pjNum");
		int pjNum2 = Integer.parseInt(pjNum);
		
		UserBoardVO vo = new UserBoardVO();
		vo.setPjNum(pjNum2);
		vo.setBoardCategory(categorySelect);
		vo.setBoardProcess(processSelect);
		vo.setBoardTitle(boardTitle);
		vo.setBoardWriter(writer);
		vo.setBoardStartdate(startDate);
		vo.setBoardEnddate(endDate);
		vo.setBoardContent(description);
		
		List<MultipartFile> mFiles = multipart.getFiles("fileUpload");
		
		List<FileVO> fvoList = new ArrayList<>();
		for (MultipartFile file : mFiles) {
			//파일명
			String origin = file.getOriginalFilename(); 
			
			//브라우저별로 경로가 포함돼서 올라오는 경우가 있어서 간단한 처리
			String fileName = origin.substring(origin.lastIndexOf("\\") + 1);
			
			//폴더 생성
			String filepath = makeDir();
			
			//중복 파일의 처리 (어떤값이 들어가더라도 중복이 안되도록)
			String boardfileUuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			
			//최종 저장 경로
			String fileUrl= filepath + "/" + boardfileUuid;
			
			//aws에 업로드
			ObjectMetadata metadata= new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());
			amazonS3Client.putObject(bucket,fileUrl,file.getInputStream(),metadata); 
			
			//db에 값 넣어주기
			FileVO fvo = new FileVO();
			fvo.setBoardfilePath(uploadpath + fileUrl);		
			fvo.setBoardfileName(file.getOriginalFilename());
			fvoList.add(fvo);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("vo", vo);
		
		userBoardService.getContent(resultMap);
		userBoardService.fileUploadList(fvoList);
		
		if(!(result == 0)) {
			msg = "등록에 성공 하였습니다.";
		}else {
			msg = "등록에 실패 하였습니다.";
		}
		
		return result;
	}
	
	//날짜별로 폴더 생성
	private String makeDir() {
		//오늘날짜
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String now = sdf.format(date);

		String path = bucket + "\\" + now + "-board"; //경로
		File file = new File(path);

		//존재하면 true 존재하지 않으면 false
		if(file.exists() == false) {
			file.mkdir(); //폴더 생성!
		}
		return now; // 년월일 폴더 위치
	}			
		
	@GetMapping("/get-obmember")
	public ArrayList<ProjectVO> getObserverMember(@RequestParam("pj_num") int pj_num){
		
		//옵저버 가져오기
		ArrayList<ProjectVO> observerList = new ArrayList<>();
		observerList = userBoardService.getObserver(pj_num);
		
		return observerList;
	}
}
