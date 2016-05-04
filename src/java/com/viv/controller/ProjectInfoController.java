package com.viv.controller;

import com.viv.Config;
import com.viv.dao.UserProjectOperation;
import com.viv.entity.*;
import com.viv.exception.ControllerException;
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
    public @ResponseBody Map<String,Object> list_json(HttpSession session,Integer pageIndex){
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (pageIndex == null) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
            User user = (User) session.getAttribute("user");
                /*检验pageIndex最大值*/
            map.put("user_id", user.getId());
            List<User_project> user_projects = projectInfoService.select(map);
            map.clear();
            int count = user_projects.size();
            Integer maxIndex = count / Config.PAGE_SIZE;
            if (pageIndex > maxIndex) {
                result = Config.ERROR;
                message = "请求的页面超出范围";
                throw new ControllerException(message);
            }

            /*数据处理*/
            Page page = new Page(SortDirectionEnum.DESC.toString(), "u.id", pageIndex, Config.PAGE_SIZE);
            map.put("page", page);
            map.put("user_id", user.getId());

            /*业务操作*/
            List<User_project> projects = projectInfoService.select(map);
            map.clear();
            result = Config.SUCCESS;
            map.put(Config.RESULT, result);
            map.put("projects", projects);

        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT,result);
            map.put(Config.MESSAGE,m.getMessage());
        }finally {
            return map;
        }

//
//
//        List<User_project> projects = null;
//        User user = (User) session.getAttribute("user");
//        int index = Integer.parseInt(pageIndex);
//        if(user != null){
//            int count = userProjectService.countByUserId(user.getId());
//            int maxIndex = count / Config.PAGE_SIZE;
//            if (index > -1 && index <= maxIndex) {
//                Page page = new Page(SortDirectionEnum.ASC.toString(),"u_p_id",index, Config.PAGE_SIZE);
//                projects = projectInfoService.selectByUserId_page(page,user.getId());
//                result = "success";
//                map.put("result",result);
//                map.put("projects",projects);
//                return map;
//            }
//            map.put("result",result);
//            return map;
//        }
//        message = "未知错误";
//        map.put("result",result);
//        map.put("message",message);
//        return map;
    }

    /*处理查询当前用户项目条数请求*/
    @RequestMapping(value = "/project/count" , params = "json")
    public @ResponseBody Map<String,Object> count_json(HttpSession session){
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/

            /*权限检验*/

            /*数据处理*/
            User user = (User) session.getAttribute("user");
            map.put("user_id", user.getId());
            List<User_project> user_projects = projectInfoService.select(map);
            map.clear();
            /*业务操作*/
            int count = user_projects.size();
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);
            map.put("count", count);

        } catch (Exception m) {
            map.clear();
            map.put(Config.RESULT,result);
            map.put(Config.MESSAGE,Config.ERROR);
        }finally {
            return map;
        }
//
//
//        if (user != null) {
//            int count = userProjectService.countByUserId(user.getId());
//            result = "success";
//            map.put("result",result);
//            map.put("count",count);
//            return map;
//        }
//        message = "未知错误";
//        map.put("result",result);
//        map.put("message",message);
//        return map;
    }
    /*请求添加页面*/
    @RequestMapping(value = "/project/add")
    public String add(){
        return "login/addProject.html";
    }

    /*处理添加请求*/
    @RequestMapping(value = "/project/add",params = "json")
    public @ResponseBody Map<String,Object> add_json(HttpSession session, Project_info project_info){
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (project_info.getName() == null || project_info.getName().trim().equals("")) {
                result = Config.ERROR;
                message = "存在不能为空的输入";
                throw new ControllerException(message);
            }
            /*权限检验*/

            /*数据处理*/
            User user = (User) session.getAttribute("user");
            if (project_info.getVersion() == null) {
                project_info.setVersion(0);
            }
            /*业务操作*/
            projectInfoService.insert(project_info,user.getId());
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);
        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT,result);
            map.put(Config.MESSAGE,m.getMessage());
        }finally {
            return map;
        }
    }

    /*处理删除请求*/
    @RequestMapping(value = "/project/delete",params = "json")
    public @ResponseBody Map<String,Object> delete_json(HttpSession session,Long projectInfoId){
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (projectInfoId == null) {
                result = Config.ERROR;
                message = "存在不能为空的输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
            User user = (User) session.getAttribute("user");
                /*检验该projectId是否属于该用户的*/
            if (!(projectInfoService.checkProject_id(projectInfoId, user.getId()))) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
            /*数据处理*/

            /*业务操作*/
            projectInfoService.delete(projectInfoId,user.getId());
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);
        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT,result);
            map.put(Config.MESSAGE,m.getMessage());
        }finally {
            return map;
        }
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
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (project_info.getId() == null) {
                result = Config.ERROR;
                message = "存在不能为空的输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
            User user = (User) session.getAttribute("user");
                /*检验该project_id是否属于该用户的*/
            if (!(projectInfoService.checkProject_id(project_info.getId(), user.getId()))) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
            /*数据处理*/

            /*业务操作*/
            projectInfoService.update(project_info);
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);
        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT,result);
            map.put(Config.MESSAGE,m.getMessage());
        }finally {
            return map;
        }
    }
}
