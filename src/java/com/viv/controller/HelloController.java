package com.viv.controller;

import com.viv.entity.*;
import com.viv.service.ProjectInfoService;
import com.viv.service.ProtoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ValueConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        projectInfoService.insert(project_info,new Long(1));
        return "insert";
    }
    @RequestMapping(value = "/test/delete")
    public @ResponseBody String index4(){
        ProjectInfoService projectInfoService = new ProjectInfoService();
        projectInfoService.delete(1,new Long(1));
        return "delete";
    }
    @RequestMapping(value = "/test/update")
    public @ResponseBody String index5(){
        ProjectInfoService projectInfoService = new ProjectInfoService();
        Project_info project_info = new Project_info();
        project_info.setId(new Long(3));
        project_info.setName("大鬼gui");
        project_info.setVersion(2);
        projectInfoService.update(project_info,new Long(1));
        return "update";
    }
    @RequestMapping(value = "/test/getUserProject")
    public @ResponseBody List<User_project> index6(){
        ProjectInfoService projectInfoService = new ProjectInfoService();
        List<User_project> projects = projectInfoService.selectByUserId(1);
        return projects;
    }
    @RequestMapping(value = "/test/getUserProject_page")
    public @ResponseBody List<User_project> index7(){
        ProjectInfoService projectInfoService = new ProjectInfoService();
        List<User_project> projects = projectInfoService.selectByUserId_page(new Page(SortDirectionEnum.DESC.toString(),"u_p_id",1,2),new Long(1));
        return projects;
    }

/*    @RequestMapping(value = "/test/proto/insert")
    public @ResponseBody String index8(){
        ProtoService service = new ProtoService();
        service.insert(new Proto(1,"测试describe","测试name","测试namespace",new Long(1)));
        return "insert";
    }*/

/*    @RequestMapping(value = "/test/proto/delete")
    public @ResponseBody String index9() {
        ProtoService service = new ProtoService();
        service.delete(new Long(14),new Long(1));
        return "delete";
    }*/

/*    @RequestMapping(value = "/test/proto/update")
    public @ResponseBody String index10() {
        ProtoService service = new ProtoService();
        Proto p = new Proto(1,"测describe","测name","测namespace",new Long(1));
        p.setId(new Long(11));
        service.update(p);
        return "update";
    }*/

    @RequestMapping(value = "/test/proto/selectById")
    public @ResponseBody Proto index11() {
        ProtoService service = new ProtoService();
        Proto proto = service.selectById(new Long(1));
        return proto;
    }

    @RequestMapping(value = "/test/proto/selectByProjectId")
    public @ResponseBody List<Proto> index12(){
        ProtoService service = new ProtoService();
        List<Proto> protos = service.selectByProjectId(new Long(1));
        return protos;
    }

    @RequestMapping(value = "/test/proto/count")
    public @ResponseBody int index13(){
        ProtoService service = new ProtoService();
        int count = service.countByProjectId(new Long(1));
        return count;

    }
   @RequestMapping(value = "/test/proto/select")
   public @ResponseBody List<Proto> index14() {
        ProtoService service = new ProtoService();
       Map<String,Object> map = new HashMap<>();
       Proto proto = new Proto();
       proto.setProject_id(new Long(1));
       proto.setDescribes("%登录%");
       Page page = new Page(SortDirectionEnum.DESC.toString(),"id",0,3);
       map.put("proto",proto);
//       map.put("page",page);
       map.put("like",1);
       List<Proto> protos = service.select(map);
        return protos;
    }

    @RequestMapping(value = "/test/proto/update")
    public @ResponseBody String index10() {
        ProtoService service = new ProtoService();
        Proto p = new Proto();
        p.setId(new Long(14));
        p.setName("562222");
        service.update(p);
        return "update";
    }

}
