package com.choongang.scheduleproject.command;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	/*
	 * @NotNull - null값만 허용하지 않음 (wrapper의 Integer, Long, String 등등)
	 * @NotBlank - null값과 공백허용하지 않음 (String에만 적용)
	 * @NotEmpty - null값을 허용하지 않음 (Array, list 적용)
	 * @Pattern - 정규표현식에 맞는 문자열을 정의할 수 있음 (String에만 적용)
	 * 
	 * @Email - 이메일형식검증 (공백은 통과)
	 * @Min - 최소값
	 * @Max - 최대값
	 */
	@NotNull // 처음엔 모든 데이터에 NotNull 처리를 해줬는데, 로그인 시 에러가 발생하여 ID와 PW에만 NotNull을 걸어뒀습니다.
	@Pattern(regexp = "^[a-zA-Z0-9]{4,12}$")
	private String user_id;
	
	private int department_id;
	
	@Email
	private String user_email;
	
	@Pattern(regexp = "[가-힣]{2,4}")
	private String user_name;
	
	@NotNull
	@Pattern(regexp = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")
	private String user_pw;
	
	@Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
	private String user_cell;
	
	private String user_birth;
	private int user_active;
	private String user_position;
	private int user_role;
	private int user_employeenumber;
	private int user_loginfail;
	private LocalDateTime user_regdate;
	private String user_img;
}

