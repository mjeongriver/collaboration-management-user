package com.choongang.scheduleproject.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminNoticeListVO {
	
	private int notice_num;
	private String admin_id;
	private String notice_title;
	private String notice_content;
	private Timestamp notice_regdate;
	private String notice_writer;
	private String notice_note;
	

}
