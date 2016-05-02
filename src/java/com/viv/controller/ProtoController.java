package com.viv.controller;

import com.viv.Config;
import com.viv.entity.Page;
import com.viv.entity.Proto;
import com.viv.entity.SortDirectionEnum;
import com.viv.entity.User;
import com.viv.exception.ControllerException;
import com.viv.service.ProjectInfoService;
import com.viv.service.ProtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
    public String insert(Long project_id, Model model) {
        Proto proto = new Proto();
        proto.setProject_id(project_id);
        model.addAttribute(proto);
        return "login/proto_add.jsp";
    }

    /*处理添加proto操作*/
    @RequestMapping(value = "/proto/insert" ,params = "json")
    public @ResponseBody Map<String,Object> insert_json(HttpSession session,Proto proto) {
        map.clear();
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

    /*处理删除proto记录操作*/
    @RequestMapping(value = "/proto/delete",params = "json")
    public @ResponseBody Map<String,Object> delete_json(HttpSession session,Proto proto) {
        map.clear();
        try {
            /*数据检验*/
            if (proto.getId()==null || proto.getProject_id() == null) {
                result = Config.ERROR;
                message = "存在不能为空的输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
                /*检查project_id是否是该用户的*/
            User user = (User) session.getAttribute("user");
            if (!(projectService.checkProject_id(proto.getProject_id(), user.getId()))) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
                /*检查该protoId是否属于project_id的*/
            Map<String,Object> m = new HashMap<>();
            Proto p = new Proto();
            p.setProject_id(proto.getProject_id());
            p.setId(proto.getId());
            m.put("proto",p);
            List<Proto> protos = protoService.select(m);
            if (protos.size() < 1) {
                result = Config.ERROR;
                message = "非法的protoId操作";
                throw new ControllerException(message);
            }
            /*数据处理*/

            /*业务操作*/
            protoService.delete(proto.getId());
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);

        } catch (ControllerException m) {
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, message);
        }finally {
            return map;
        }

    }

    /*获取修改proto记录页面*/
    @RequestMapping(value = "/proto/update")
    public String update(Proto proto,Model model) {
        model.addAttribute(proto);
        return "login/proto_update.jsp";
    }

    /*处理proto更新操作*/
    @RequestMapping(value = "/proto/update",params = "json")
    public @ResponseBody Map<String,Object> update_json(HttpSession session,Proto proto) {
        map.clear();
        try{
            /*数据检验*/
            if (proto.getId() == null || proto.getProject_id() == null) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
                /*检查project_id是否是该用户的*/
            User user = (User) session.getAttribute("user");
            if (!(projectService.checkProject_id(proto.getProject_id(), user.getId()))) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
                /*检查该protoId是否属于project_id的*/
            Map<String,Object> m = new HashMap<>();
            Proto p = new Proto();
            p.setProject_id(proto.getProject_id());
            p.setId(proto.getId());
            m.put("proto",p);
            List<Proto> protos = protoService.select(m);
            if (protos.size() < 1) {
                result = Config.ERROR;
                message = "非法的protoId操作";
                throw new ControllerException(message);
            }
            /*数据处理*/

            /*业务操作*/
            protoService.update(proto);
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);
        }catch (ControllerException m){
            map.put(Config.RESULT,result);
            map.put(Config.MESSAGE,message);
        }finally {
            return map;
        }
    }

    /*获取proto列表页面*/
    @RequestMapping(value = "/proto/list")
    public String list(Long project_id,Model model) {
        Proto proto = new Proto();
        proto.setProject_id(project_id);
        model.addAttribute(proto);
        System.out.println(project_id);
        return "login/proto_list.jsp";
    }

    /*获取proto中project_id为xx的count*/
    @RequestMapping(value = "/proto/count",params = "json")
    public @ResponseBody Map<String,Object> count_json(HttpSession session,Long project_id) {
        map.clear();
        try {
            /*数据检验*/
            if (project_id == null || project_id.equals("")) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
                /*检查project_id是否是该用户的*/
            User user = (User) session.getAttribute("user");
            if (!(projectService.checkProject_id(project_id, user.getId()))) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
            /*数据处理*/
            Map<String,Object> m = new HashMap<>();
            Proto p = new Proto();
            p.setProject_id(project_id);
            m.put("proto", p);
            /*业务操作*/
            int count = protoService.select(m).size();
            result = Config.SUCCESS;
            map.put(Config.RESULT, result);
            map.put("count", count);

        } catch (ControllerException m) {
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, message);
        }finally {
            return map;
        }

    }


    /*处理分页获取proto列表请求*/
    @RequestMapping(value = "/proto/list",params = "json")
    public @ResponseBody Map<String,Object> list_json(HttpSession session,Integer pageIndex,Long project_id) {
        map.clear();
        try {
            /*数据检验*/
            if (pageIndex == null || pageIndex.equals("") || project_id == null || project_id.equals("")) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
                /*检查project_id是否是该用户的*/
            User user = (User) session.getAttribute("user");
            if (!(projectService.checkProject_id(project_id, user.getId()))) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
                /*检验pageIndex最大值*/
            Map<String,Object> m = new HashMap<>();
            Proto p = new Proto();
            p.setProject_id(project_id);
            m.put("proto", p);
            int count = protoService.select(m).size();
            Integer maxIndex = count / Config.PAGE_SIZE;
            if (pageIndex > maxIndex) {
                result = Config.ERROR;
                message = "请求的页面超出范围";
                throw new ControllerException(message);
            }
            /*数据处理*/
            Page page = new Page(SortDirectionEnum.DESC.toString(), "id", pageIndex, Config.PAGE_SIZE);
            m.put("page", page);
            /*业务操作*/
            List<Proto> protos = protoService.select(m);
            result = Config.SUCCESS;
            map.put(Config.RESULT, result);
            map.put("protos", protos);

        } catch (ControllerException m) {
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, message);
        }finally {
            return map;
        }

    }


}
