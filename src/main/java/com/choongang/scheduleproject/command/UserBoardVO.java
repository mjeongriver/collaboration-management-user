package com.choongang.scheduleproject.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardVO {

	private int board_num;
	private int pj_num;
	private String board_title;
	private String board_writer;
	private Timestamp board_regdate;
	private Boolean board_process;
	private String board_startdate;
	private String board_enddate;
	private String board_targetdate;
	private String board_content;
	private String board_category;
	
}
