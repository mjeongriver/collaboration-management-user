package com.choongang.scheduleproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choongang.scheduleproject.command.ProjectMemberVO;
import com.choongang.scheduleproject.command.ProjectVO;
import com.choongang.scheduleproject.project.service.ProjectService;
import com.google.gson.Gson;



@RestController
public class ProjectAjaxController {

   @Autowired
   private ProjectService projectService;
   
   private ProjectVO vo;
   
   //부서 요청
   @GetMapping("/getDepList") 
   public List<ProjectVO> getDepList (){
      return projectService.getDepList();
   }
   
   //부서별 팀원 요청
   @GetMapping("/getDepMemberList")
   public List<ProjectVO> getDepMemberList(@RequestParam("department_id") int department_id) {
//      ProjectVO vo = ProjectVO.builder()
//                        .department_id(department_id)
//                        .build();
//      System.out.println("getDepMemberList : " + projectService.getDepMemberList(department_id).toString());

      return projectService.getDepMemberList(department_id);
   }
   
   //등록 요청
   @PostMapping("/regProject")
   public String regist(@RequestParam Map<String, Object> map, RedirectAttributes ra) {
//	   	System.out.println("등록 start =======");
//      System.out.println((String)map.get("pj_name")); //Object여서 형 변환
//      System.out.println((String)map.get("pj_startdate"));
//      System.out.println((String)map.get("pj_enddate"));
//      System.out.println((String)map.get("pj_description"));
        
        ProjectVO vo = new ProjectVO();
        vo.setPj_name((String)map.get("pj_name"));
        vo.setPj_startdate((String)map.get("pj_startdate"));
        vo.setPj_enddate((String)map.get("pj_enddate"));
        vo.setPj_description((String)map.get("pj_description"));
        
        int result = projectService.regist(vo);
        
        String msg = result == 1 ? "정상 입력 되었습니다." : "등록에 실패하였습니다";
        ra.addFlashAttribute("msg", msg);
        System.out.println(result);
        System.out.println(vo.getPj_name());
        System.out.println(vo.toString());
        return "success";
        //return "redirect:/project/projectStarted"; 
   };
   
   @PostMapping("/regProjectMember")
   public String memberRegist(@RequestBody Map<String, Object> map, RedirectAttributes ra) {
       Gson gson = new Gson();
       ProjectMemberVO pvo = new ProjectMemberVO();

       List<Map<String, Object>> user_list =  gson.fromJson((String)map.get("pj_userid"), List.class);
       System.out.println("userlist size : " + user_list.size());

        //팀원 아이디
       List<String> userIdList = (List<String>) map.get("pj_userid[]");
       for (int i = 0; i < userIdList.size(); i++) {
       pvo.setUser_id(userIdList.get(i));
       }

        //프로젝트 번호
       int pj_num = Integer.parseInt((String) map.get("pj_num"));
       pvo.setPj_num(pj_num);
       System.out.println(1111111111);
       int result2 = projectService.registMember(pvo);
       System.out.println(result2);

       System.out.println(result2);
       System.out.println(pvo.toString());
       return "redirect:/project/projectStarted"; 
   }
   
   
   
}