package com.choongang.scheduleproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.choongang.scheduleproject.util.interceptor.UserAuthHandler;


@Configuration //스프링설정파일
public class WebConfig implements WebMvcConfigurer {

	//프리핸들러
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}

	//인터셉터추가
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(userAuthHandler())
//				.addPathPatterns("/main")
//				.addPathPatterns("/product/*")
//				.addPathPatterns("/user/*")
//				.excludePathPatterns("/user/login")
//				.excludePathPatterns("/user/join");
		
//				.addPathPatterns("/**")
//				.excludePathPatterns("/user/login")
//				.excludePathPatterns("/user/join")
//				.excludePathPatterns("/js/*")
//				.excludePathPatterns("/css/*")
//				.excludePathPatterns("/img/*");
				//Rest API 패스에서 제외..?
//		registry.addInterceptor(userAuthHandler())
//				.addPathPatterns("/*")
//				.addPathPatterns("/project/*")
//				.addPathPatterns("/userboards/*")
//				.addPathPatterns("/user/*")
//				.excludePathPatterns("/user/userLogin")
//				.excludePathPatterns("/user/userFindId")
//				.excludePathPatterns("/user/userRegister")
//				.excludePathPatterns("/user/userResetPw")
//				.excludePathPatterns("/user/Login")
//				.excludePathPatterns("/user/kakao")
//				.excludePathPatterns("/user/RegisterForm")
//				.excludePathPatterns("/user/FindId")
//				.excludePathPatterns("/user/ResetPw")
//				.excludePathPatterns("/user/ChangeInfo")
//				.excludePathPatterns("/admin/noticeLogin")
//				.excludePathPatterns("/checkAllId") //rest api
//				.excludePathPatterns("/checkAllEmail") //rest api
//				.excludePathPatterns("/getAllDepartment") //rest api
//				.excludePathPatterns("/sendMail") //rest api
//				.excludePathPatterns("/verifyMail") //rest api
//				.excludePathPatterns("/checkIdAndEmail") //rest api
//				.excludePathPatterns("/checkNameAndEmail"); //rest api
//		
				
				
		
		
	}
	
	
	
}
