package com.choongang.scheduleproject.command;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ProjectVO {
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String pj_startdate;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String pj_enddate;
	
	@NotNull
	private String pj_name;
	
	@NotNull
	private String pj_description;
	
	private int pj_num;
	private String user_name;
	private String user_id;
	private String pj_writer;
	private boolean pj_active;
	private boolean pj_bookmark;
	private String department_name;
	private int department_id;
	private boolean is_observer; 
	private int pj_totalMember;
	private int pj_observerCount;
	private int pj_memberCount;
	
}
