package com.choongang.scheduleproject.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.choongang.scheduleproject.command.UserVO;
import com.choongang.scheduleproject.user.service.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/AWSUpload")
@RequiredArgsConstructor
public class AWSUploadController {

	private final AmazonS3Client amazonS3Client;

	@Autowired
	private UserMapper userMapper;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	//업로드 패스
	@Value("${project.uploadpath}")
	private String uploadpath;

	@PostMapping("/profileUpload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, HttpSession session, RedirectAttributes ra) throws URISyntaxException {
		try {
			
			
			if(file.getContentType().contains("image") == false) {
				session.invalidate(); // 세션 만료시키기
				ra.addFlashAttribute("msg", "png, jpg, jpeg 형식만 등록 가능합니다.");
				URI redirectUri = new URI("http://localhost:8686/user/userLogin");
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setLocation(redirectUri);
				return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
			}
			
			//파일명
			String origin = file.getOriginalFilename(); 
			//브라우저별로 경로가 포함돼서 올라오는 경우가 있어서 간단한 처리
			String filename = origin.substring(origin.lastIndexOf("\\") + 1);
			//폴더 생성
			String filepath = makeDir();
			//중복 파일의 처리 (어떤값이 들어가더라도 중복이 안되도록)
			String uuid = UUID.randomUUID().toString();
			//최종 저장 경로
			String fileUrl= filepath + "/" + uuid + "_"+ filename;

			//aws에 업로드
			ObjectMetadata metadata= new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());
			amazonS3Client.putObject(bucket,fileUrl,file.getInputStream(),metadata);

			//db에 파일경로 업로드
			String user_id = (String)session.getAttribute("user_id");
			UserVO vo = new UserVO();
			vo.setUser_id(user_id);
			vo.setUser_img(uploadpath + fileUrl);

			int result = userMapper.insertImg(vo);

			String msg = result == 1 ? "이미지 업로드에 성공하였습니다. 다시 로그인해주세요." : "이미지 업로드에 실패했습니다. 관리자에게 문의하세요.";
			ra.addFlashAttribute("msg", msg);
			session.invalidate(); // 세션 만료시키기

			URI redirectUri = new URI("http://localhost:8686/user/userLogin");
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(redirectUri);
			return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	//날짜별로 폴더 생성
	private String makeDir() {
		//오늘날짜
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String now = sdf.format(date);

		String path = bucket + "\\" + now; //경로
		File file = new File(path);

		//존재하면 true 존재하지 않으면 false
		if(file.exists() == false) {
			file.mkdir(); //폴더 생성!
		}

		return now; // 년월일 폴더 위치

	}

}