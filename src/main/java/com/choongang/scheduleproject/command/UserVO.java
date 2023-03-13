package com.choongang.scheduleproject.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
   private String user_id;
   private int department_id;
   private boolean user_role;
   private String user_email;
   private String user_name;
   private String user_pw;
   private String user_cell;
   private String user_birth;
   private boolean user_active;
   private String user_position;
   private int employee_number;
   private String department_name;
}