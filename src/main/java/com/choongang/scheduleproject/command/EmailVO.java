package com.choongang.scheduleproject.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVO {
	private String email;
	private String code;
	private LocalDateTime expire_time;
	private String joinReset;
}
