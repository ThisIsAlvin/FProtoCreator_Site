package com.viv.controller;

import com.viv.Config;
import com.viv.dao.UserProjectOperation;
import com.viv.entity.*;
import com.viv.service.ProjectInfoService;
import com.viv.service.UserProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viv on 16-4-24.
 */
@Controller
@RequestMapping(value = "/login")
public class ProjectInfoController {
    private ProjectInfoService projectInfoService;
    private UserProjectService userProjectService;
    public ProjectInfoController(){
        projectInfoService = new ProjectInfoService();
        userProjectService = new UserProjectService();
    }

    /*显示主页页面，合法session*/
    @RequestMapping(value = "/project/list")
    public String list(){
        return "login/myIndex.html";
    }

    /*处理获取projectInfo列表请求*/
    @RequestMapping(value = "/project/list" , params = "json")
    public @ResponseBody Map<String,Object> list_json(HttpSession session,String pageIndex){
        String result = "error";
        String  message = "error";
        Map<String,Object> map = new HashMap<String,Object>();
        List<User_project> projects = null;
        User user = (User) session.getAttribute("user");
        int index = Integer.parseInt(pageIndex);
        if(user != null){
            int count = userProjectService.countByUserId(user.getId());
            int maxIndex = count / Config.PAGE_SIZE;
            if (index > -1 && index <= maxIndex) {
                Page page = new Page(SortDirectionEnum.ASC.toString(),"u_p_id",index, Config.PAGE_SIZE);
                projects = projectInfoService.selectByUserId_page(page,user.getId());
                result = "success";
                map.put("result",result);
                map.put("projects",projects);
                return map;
            }
            map.put("result",result);
            return map;
        }
        message = "未知错误";
        map.put("result",result);
        map.put("message",message);
        return map;
    }

    /*处理查询当前用户项目条数请求*/
    @RequestMapping(value = "/project/count" , params = "json")
    public @ResponseBody Map<String,Object> count_json(HttpSession session){
        String result = "error";
        String  message = "error";
        Map<String,Object> map = new HashMap<String,Object>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int count = userProjectService.countByUserId(user.getId());
            result = "success";
            map.put("result",result);
            map.put("count",count);
            return map;
        }
        message = "未知错误";
        map.put("result",result);
        map.put("message",message);
        return map;
    }
    /*请求添加页面*/
    @RequestMapping(value = "/project/add")
    public String add(){
        return "login/addProject.html";
    }

    /*处理添加请求*/
    @RequestMapping(value = "/project/add",params = "json")
    public @ResponseBody Map<String,Object> add_json(HttpSession session, Project_info project_info){
        String result = "error";
        String message = "error";
        Map<String,Object> map = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        if(user != null){
            projectInfoService.insert(project_info,user.getId());
            result = "success";
            map.put("result",result);
            map.put("project_info",project_info);
            return map;
        }
        message = "未知错误";
        map.put("result",result);
        map.put("message",message);
        return map;
    }

    /*处理删除请求*/
    @RequestMapping(value = "/project/delete",params = "json")
    public @ResponseBody Map<String,Object> delete_json(HttpSession session,Integer projectInfoId){
        String result = "error";
        String message = "error";
        Map<String,Object> map = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        if(user!=null && projectInfoId !=null){
            projectInfoService.delete(projectInfoId,user.getId());
            result = "success";
            map.put("result",result);
            return map;
        }
        return map;
    }
    /*显示修改页面*/
    @RequestMapping(value = "/project/update")
    public String update(Project_info project_info,Model model){
        model.addAttribute(project_info);
        return "login/updateProject.jsp";
    }
    /*处理修改请求*/
    @RequestMapping(value = "/project/update",params = "json")
    public @ResponseBody Map<String ,Object>  update_json(HttpSession session, Project_info project_info){
        String result = "error";
        String message = "error";
        Map<String,Object> map = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        if(user !=null && project_info !=null && project_info.getId() != null){
            projectInfoService.update(project_info,user.getId());
            result = "success";
            map.put("result",result);
            return map;
        }
        return map;
    }
}
