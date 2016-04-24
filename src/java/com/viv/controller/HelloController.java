package com.viv.controller;

import com.viv.service.ProjectInfoService;
import com.viv.entity.Project_info;
import com.viv.entity.User_project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by viv on 16-3-28.
 * 该类在新建工程的时候用于测试工程是否建立成功
 * http://localhost:8080/1.do
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public  String index() {
        return "index/index.html";
    }
    @RequestMapping(value = "/test/select")
    public @ResponseBody  Project_info index2() {
       ProjectInfoService projectInfoService = new ProjectInfoService();
        Project_info project_info = projectInfoService.selectById(1);
        System.out.println(project_info);
        return project_info;
    }
    @RequestMapping(value = "/test/insert")
    public @ResponseBody String index3(){
        ProjectInfoService projectInfoService = new ProjectInfoService();
        Project_info project_info = new Project_info();
        project_info.setName("大风打分");
        project_info.setVersion(1);
        projectInfoService.insert(project_info,1);
        return "insert";
    }
    @RequestMapping(value = "/test/delete")
    public @ResponseBody String index4(){
        ProjectInfoService projectInfoService = new ProjectInfoService();
        projectInfoService.delete(1,1);
        return "delete";
    }
    @RequestMapping(value = "/test/update")
    public @ResponseBody String index5(){
        ProjectInfoService projectInfoService = new ProjectInfoService();
        Project_info project_info = new Project_info();
        project_info.setId(3);
        project_info.setName("大鬼gui");
        project_info.setVersion(2);
        projectInfoService.update(project_info,1);
        return "update";
    }
    @RequestMapping(value = "/test/getUserProject")
    public @ResponseBody List<User_project> index6(){
        ProjectInfoService projectInfoService = new ProjectInfoService();
        List<User_project> projects = projectInfoService.selectByUserId(1);
        return projects;
    }


}
