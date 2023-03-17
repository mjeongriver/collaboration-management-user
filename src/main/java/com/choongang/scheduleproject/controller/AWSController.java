package com.choongang.scheduleproject.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.choongang.scheduleproject.command.UserVO;
import com.choongang.scheduleproject.user.service.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/aws")
@RequiredArgsConstructor
public class AWSController {

	private final AmazonS3Client amazonS3Client;

	@Autowired
	private UserMapper userMapper;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	//업로드 패스
	@Value("${project.uploadpath}")
	private String uploadpath;

	@PostMapping("/profile-upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, HttpSession session, RedirectAttributes ra) throws URISyntaxException {
		try {
			if(file.getContentType().contains("image") == false) {
				ra.addFlashAttribute("msg", "png, jpg, jpeg 형식만 등록 가능합니다.");
				URI redirectUri = new URI("http://localhost:8686/user/user-mypage");
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
			//메시지 담아서 리다이렉트
			String msg = result == 1 ? "이미지 업로드에 성공하였습니다." : "이미지 업로드에 실패했습니다. 관리자에게 문의하세요.";
			ra.addFlashAttribute("msg", msg);
			URI redirectUri = new URI("http://localhost:8686/user/user-mypage");
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

	@GetMapping("/profile-download")
	public ResponseEntity<byte[]> downloadFile(@RequestParam("myProfileImg") String myProfileImg) throws IOException { // 객체 다운 fileUrl : 폴더명/파일네임.파일확장자

		String fileUrl = myProfileImg.substring(54); // 객체 URL에서 객체 키를 꺼내야 함
		String fileName = myProfileImg.substring(98); // 다운로드할 때는 파일명만 나오게 하기 위함

		S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, fileUrl));
		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectInputStream);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(contentType(fileUrl));
		httpHeaders.setContentLength(bytes.length);
		String[] arr = fileName.split("/"); // 요 부분을 처리해야 파일명만 가져올 수 있음
		String type = arr[arr.length -1];
		fileUrl = URLEncoder.encode(type, "UTF-8");
		httpHeaders.setContentDispositionFormData("Content-Disposition", fileUrl );

		return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
	}

	private MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length - 1];

		switch (type) {
		case "txt":
			return MediaType.TEXT_PLAIN;
		case "png":
			return MediaType.IMAGE_PNG;
		case "jpg":
			return MediaType.IMAGE_JPEG;
		default:
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}


}