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
		// 선생님꺼
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
//				.excludePathPatterns("/user/user-login")
//				.excludePathPatterns("/user/user-find-id")
//				.excludePathPatterns("/user/user-register")
//				.excludePathPatterns("/user/user-reset-pw")
//				.excludePathPatterns("/user/login")
//				.excludePathPatterns("/user/kakao")
//				.excludePathPatterns("/user/register-form")
//				.excludePathPatterns("/user/find-id")
//				.excludePathPatterns("/user/reset-pw")
//				.excludePathPatterns("/user/change-info")
//				.excludePathPatterns("/admin/noticeLogin")
//				.excludePathPatterns("/check-all-id") //rest api
//				.excludePathPatterns("/check-all-email") //rest api
//				.excludePathPatterns("/get-all-department") //rest api
//				.excludePathPatterns("/send-mail") //rest api
//				.excludePathPatterns("/verify-mail") //rest api
//				.excludePathPatterns("/check-id-and-email") //rest api
//				.excludePathPatterns("/check-name-and-email"); //rest api
//		
				
				
		
		
	}
	
	
	
}
