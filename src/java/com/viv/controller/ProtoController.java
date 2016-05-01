package com.viv.controller;

import com.viv.Config;
import com.viv.entity.Proto;
import com.viv.entity.User;
import com.viv.exception.ControllerException;
import com.viv.service.ProjectInfoService;
import com.viv.service.ProtoService;
import org.junit.runner.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by viv on 16-5-1.
 */
@Controller
@RequestMapping(value = "/login")
public class ProtoController {
    private ProtoService protoService;
    private ProjectInfoService projectService;
    private Map<String,Object> map;
    private String result = Config.ERROR;
    private String message = Config.ERROR;

    public ProtoController() {
        protoService = new ProtoService();
        projectService = new ProjectInfoService();
        map = new HashMap<>();
    }
    /*获取添加proto页面*/
    @RequestMapping(value = "/proto/insert")
    public String insert() {
        return "login/proto_add.html";
    }

    /*处理添加proto操作*/
    @RequestMapping(value = "/proto/insert" ,params = "json")
    public @ResponseBody Map<String,Object> insert_json(HttpSession session,Proto proto) {
        try {
        /*数据检验*/
            if (proto.getProject_id()==null || proto.getCmd()==null || proto.getName().equals("")) {
                result = Config.ERROR;
                message = "存在不能为空的输入";
                throw new ControllerException(message);
            }
        /*权限检验*/
            /*检查project_id是否属于当前用户*/
            User user = (User) session.getAttribute("user");
            if (!(projectService.checkProject_id(proto.getProject_id(), user.getId()))) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }

        /*数据处理*/

        /*业务操作*/
            protoService.insert(proto);
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);

        } catch (ControllerException m) {
            map.put(Config.RESULT,result);
            map.put(Config.MESSAGE,message);
        }finally {
            return map;
        }
    }

//    /*处理删除proto记录操作*/
//    @RequestMapping(value = "/proto/delete",params = "json")
//    public @ResponseBody Map<String,Object> delete_json(HttpSession session,Proto proto) {
//        try {
//            /*数据检验*/
//            if (proto.getId()==null || proto.getProject_id() == null) {
//                result = Config.ERROR;
//                message = "存在不能为空的输入";
//                throw new ControllerException(message);
//            }
//            /*权限检验*/
//                /*检查project_id是否是该用户的*/
//            User user = (User) session.getAttribute("user");
//            if (!(projectService.checkProject_id(proto.getProject_id(), user.getId()))) {
//                result = Config.ERROR;
//                message = "非法的projectId操作";
//                throw new ControllerException(message);
//            }
//                /*检查该protoId是否属于project_id的*/
//
//
//            /*数据处理*/
//
//            /*业务操作*/
//            protoService.delete();
//
//        } catch (ControllerException m) {
//            map.put(Config.RESULT, result);
//            map.put(Config.MESSAGE, message);
//        }finally {
//            return map;
//        }
//
//    }
//
//    /*获取修改proto记录页面*/
//    @RequestMapping(value = "/proto/update")
//    public String update() {
//        return "login/proto_update.jsp";
//    }
//
//    /*处理proto记录操作*/
//    @RequestMapping(value = "/proto/update",params = "json")
//    public @ResponseBody Map<String,Object> update_json(HttpSession) {
//
//    }
//
//    /*获取proto列表页面*/
//    @RequestMapping(value = "/proto/list")
//    public String list() {
//        return "login/proto_list.html";
//    }
//
//    /*获取proto中project_id为xx的count*/
//    @RequestMapping(value = "/proto/count",params = "json")
//    public @ResponseBody Map<String,Object> count_json(HttpSession session,Long project_id) {
//
//    }
//
//
//    /*处理分页获取proto列表请求*/
//    @RequestMapping(value = "/proto/list",params = "json")
//    public @ResponseBody Map<String,Object> list_json(HttpSession session,Integer pageIndex) {
//
//    }


}
