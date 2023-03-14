package com.choongang.scheduleproject.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberVO {
	private int mnum;
	private int pj_num;
	private String user_id;
	private String is_observer; 
}
