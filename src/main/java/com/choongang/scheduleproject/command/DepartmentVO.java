package com.choongang.scheduleproject.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentVO {
	//@JsonProperty("department_id")
	private int departmentId;
	private String departmentName;
}
