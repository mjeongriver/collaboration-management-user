package com.choongang.scheduleproject.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentVO {
	@JsonProperty("department_id")
	private int departmentId;
	private String departmentName;
}
