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
	private String pjStartdate;

	@NotNull
	@NotBlank
	@NotEmpty
	private String pjEnddate;

	@NotNull
	private String pjName;

	@NotNull
	private String pjDescription;

	private int pjNum;
	private String userName;
	private String userId;
	private String pjWriter;
	private boolean pjActive;
	private boolean pjBookmark;
	private String departmentName;
	private int departmentId;
	private boolean isObserver;
	private int pjTotalmember;
	private int pjObservercount;
	private int pjMembercount;

}
