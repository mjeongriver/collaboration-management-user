package com.choongang.scheduleproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileVO {
	private int boardfileNum;
	private int boardNum;
	private String boardfilePath;
	private String boardfileName;
	//파일명 중복 방지를 위한 UUID 적용
	private String boardfileUuid;	
}
