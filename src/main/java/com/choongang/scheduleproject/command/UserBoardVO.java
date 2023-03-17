package com.choongang.scheduleproject.command;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardVO {

	private int boardNum;
	
	@JsonProperty("pj_num")
	private int pjNum;
	private String boardTitle;
	private String boardWriter;
	private String boardStartdate;
	private String boardEnddate;
	private String boardTargetdate;
	private String boardContent;
	private String boardCategory;
	private Boolean boardProcess;
	private Timestamp boardRegdate;
	
}
