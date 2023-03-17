package com.choongang.scheduleproject.command;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	@JsonProperty("pj_startdate")
	private String pjStartdate;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@JsonProperty("pj_enddate")
	private String pjEnddate;
	
	@NotNull
	@JsonProperty("pj_name")
	private String pjName;
	
	@NotNull
	@JsonProperty("pj_description")
	private String pjDescription;
	
	@JsonProperty("pj_num")
	private int pjNum;
	
	private String userName;
	private String userId;
	
	@JsonProperty("pj_writer")
	private String pjWriter;
	private boolean pjActive;
	private boolean pjBookmark;
	private String departmentName;

	
	//@JsonProperty("department_id")
	private int departmentId;
	
	@JsonProperty("is_observer")
	private boolean isObserver; 
	private int pjTotalMember;
	private int pjObserverCount;
	private int pjMemberCount;
	

}
