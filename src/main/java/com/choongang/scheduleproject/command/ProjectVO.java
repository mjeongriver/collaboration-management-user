package com.choongang.scheduleproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ProjectVO {
	
	private int pj_num;
	private String user_name;
	private String user_id;
	private int pj_statistics_num;
	private String pj_startdate;
	private String pj_enddate;
	private String pj_name;
	private boolean pj_active;
	private boolean pj_bookmark;
	private String pj_description;
	private String department_name;
	private int department_id;
	private boolean isObserver; 

}
