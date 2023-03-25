package com.choongang.scheduleproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistCommentVO {
	private int boardNum;
	private int pjNum;
	private String commentWriter;
	private String commentContent;
	private int commentUpperNum;
}
