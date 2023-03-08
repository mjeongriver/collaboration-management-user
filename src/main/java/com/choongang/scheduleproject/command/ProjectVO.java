package com.choongang.scheduleproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectVO {
	
	private int pj_num;
	private int statistics_num;
	private String pj_name;
	private String pj_active;
	private String pj_member;
	private String pj_bookmark;
	private String pj_description;
	

}
