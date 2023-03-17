package com.choongang.scheduleproject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
      return projectService.getDepMemberList(department_id);
   }

   //등록 요청
   @PostMapping("/regProject")
   @ResponseBody
   public Map<String, Object> regist(@RequestBody Map<String, Object> map, RedirectAttributes ra) {

	  String msg = "";
      int result1 = 0;
      int result2 = 0;
	  Map<String, Object> resultMap = new HashMap<String, Object>();

	  //데이터 확인
//	  System.out.println("등록 start =======");
//    System.out.println((String)map.get("pj_name")); //Object여서 형 변환
//    System.out.println((String)map.get("pj_startdate"));
//    System.out.println((String)map.get("pj_enddate"));
//    System.out.println((String)map.get("pj_description"));

      ProjectVO vo = new ProjectVO();
      vo.setPj_name((String)map.get("pj_name"));
      vo.setPj_writer((String)map.get("pj_writer"));
      vo.setPj_startdate((String)map.get("pj_startdate"));
      vo.setPj_enddate((String)map.get("pj_enddate"));
      vo.setPj_description((String)map.get("pj_description"));

      Gson gson = new Gson();

      List<Map<String, Object>> user_list =  gson.fromJson((String)map.get("user_boolean"), List.class);
      System.out.println("user_list size : " + user_list.size());


      //프로젝트 생성
      result1 = projectService.regist(vo);
      System.out.println("result1 : " + result1);
      int pj_num = vo.getPj_num();
      System.out.println("pj_num : " + pj_num);

      //프로젝트 멤버들 생성
      for (int i = 0; i < user_list.size(); i++) {
    	  ProjectMemberVO pmvo = new ProjectMemberVO();

    	  pmvo.setPj_num(pj_num);
    	  pmvo.setUser_id(user_list.get(i).get("team_id").toString());
    	  pmvo.setIs_observer(user_list.get(i).get("is_observer").toString());

    	  result2 = projectService.registMember(pmvo);
      }

      if(!(result1 == 0 && result2 ==0)) {
    	  msg = "등록에 성공 하였습니다.";
      }else {
    	  msg = "등록에 실패 하였습니다.";
      }

      resultMap.put("msg", msg);

      return resultMap;
   };

   //프로젝트 리스트 받아오기
   /***
    *
    * @param session 세션아이디
    * @return 페이지리스트
    */
   @GetMapping("/get-project-list")
   public ArrayList<ProjectVO> getProjectList(HttpSession session){
	  String user_id = (String)session.getAttribute("user_id");
	   return projectService.getProjectList(user_id);
   }
   //북마크 변경
   @GetMapping("/change-bookmark")
   public int changeBookmark(@RequestParam("user_id") String user_id,
		   					 @RequestParam("pj_num") int pj_num,
		   					 @RequestParam("pj_bookmark") boolean pj_bookmark) {

	   projectService.changeBookmark(user_id, pj_num, pj_bookmark);

	   return pj_num;
   }

   //프로젝트 삭제
   @GetMapping("/delete-project")
   public int deleteProject(@RequestParam("pj_num") int pj_num) {

	   projectService.deleteProject(pj_num);

	   return pj_num;
   }

}