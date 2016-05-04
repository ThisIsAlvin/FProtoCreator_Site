package com.viv.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.viv.Config;
import com.viv.entity.*;
import com.viv.exception.ControllerException;
import com.viv.service.ProjectInfoService;
import com.viv.service.ProtoFieldService;
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
public class ProtoFieldController {
    private ProtoFieldService protoFieldService;
    private ProtoService protoService;
    private ProjectInfoService projectService;


    public ProtoFieldController() {
        protoFieldService = new ProtoFieldService();
        protoService = new ProtoService();
        projectService = new ProjectInfoService();
    }

    /*获取proto_field页面*/
    @RequestMapping(value = "/proto_field/list")
    public String list(Long proto_id, Model model) {
        Proto_field proto_field = new Proto_field();
        proto_field.setProto_id(proto_id);
        model.addAttribute(proto_field);
        return "login/protoField_list.jsp";
    }

    /*获取proto_field中proto_id为xx的count*/
    @RequestMapping(value = "/proto_field/count", params = "json")
    public @ResponseBody Map<String, Object> count_json(HttpSession session,Long proto_id) {
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (proto_id == null) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
            User user = (User) session.getAttribute("user");
                /*获取该proto_id关联的project_id*/
            Proto p = new Proto();
            p.setId(proto_id);
            map.put("proto", p);
            List<Proto> protos = protoService.select(map);
            map.clear();
            if (protos.size() <= 0) {
                result = Config.ERROR;
                message = "非法的protoId操作";
                throw new ControllerException(message);
            }
                /*检验关联的project_id是否属于该用户（只要有一个关联，就通过）*/
            int i = 0;
            for (i = 0; i < protos.size(); i++) {
                if (projectService.checkProject_id(protos.get(i).getProject_id(), user.getId())) {
                    break;
                }
            }
            if (!(i < protos.size())) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }

            /*数据处理*/
            Proto_field proto_field = new Proto_field();
            proto_field.setProto_id(proto_id);
            map.put("proto_field", proto_field);
            /*业务操作*/
            int count = protoFieldService.select(map).size();
            result = Config.SUCCESS;
            map.clear();
            map.put(Config.RESULT, result);
            map.put("count", count);

        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, m.getMessage());
        } finally {
            return map;
        }
    }

    /*处理获取proto_field列表请求*/
    @RequestMapping(value = "/proto_field/list", params = "json")
    public @ResponseBody Map<String, Object> list_json(HttpSession session, Integer pageIndex, Long proto_id) {
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (pageIndex == null || proto_id == null) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
            User user = (User) session.getAttribute("user");
                /*获取该proto_id关联的project_id*/
            Proto p = new Proto();
            p.setId(proto_id);
            map.put("proto", p);
            List<Proto> protos = protoService.select(map);
            map.clear();
            if (protos.size() <= 0) {
                result = Config.ERROR;
                message = "非法的protoId操作";
                throw new ControllerException(message);
            }
                /*检验关联的project_id是否属于该用户（只要有一个关联，就通过）*/
            int i = 0;
            for (i = 0; i < protos.size(); i++) {
                if (projectService.checkProject_id(protos.get(i).getProject_id(), user.getId())) {
                    break;
                }
            }
            if (!(i < protos.size())) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }

            /*数据处理*/
            Page page = new Page(SortDirectionEnum.DESC.toString(), "id", pageIndex, Config.PAGE_SIZE);
            Proto_field proto_field = new Proto_field();
            proto_field.setProto_id(proto_id);
            map.put("page", page);
            map.put("proto_field", proto_field);

            /*业务操作*/
            List<Proto_field> protoFields = protoFieldService.select(map);
            map.clear();
            result = Config.SUCCESS;
            map.put(Config.RESULT, result);
            map.put("protoFields", protoFields);


        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, m.getMessage());
        } finally {
            return map;
        }
    }

    /*获取添加页面*/
    @RequestMapping(value = "/proto_field/insert")
    public String insert(Long proto_id, Model model) {
        Proto_field proto_field = new Proto_field();
        proto_field.setProto_id(proto_id);
        model.addAttribute(proto_field);
        return "login/protoField_add.jsp";
    }

    /*处理添加操作*/
    @RequestMapping(value = "/proto_field/insert", params = "json")
    public @ResponseBody Map<String, Object> insert_json(HttpSession session, Proto_field proto_field) {
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (proto_field.getProto_id() == null || proto_field.getName() == null || proto_field.getType() == null) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }

            /*权限检验*/
            User user = (User) session.getAttribute("user");
                /*获取该proto_id关联的project_id*/
            Proto p = new Proto();
            p.setId(proto_field.getProto_id());
            map.put("proto", p);
            List<Proto> protos = protoService.select(map);
            map.clear();
            if (protos.size() <= 0) {
                result = Config.ERROR;
                message = "非法的protoId操作";
                throw new ControllerException(message);
            }
                /*检验关联的project_id是否属于该用户（只要有一个关联，就通过）*/
            int i = 0;
            for (i = 0; i < protos.size(); i++) {
                if (projectService.checkProject_id(protos.get(i).getProject_id(), user.getId())) {
                    break;
                }
            }
            if (!(i < protos.size())) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
                /*如果extend字段不为空，就得检验该extend字段是否合法*/
            if (proto_field.getExtend() != null) {
                Proto_field prf = new Proto_field();
                prf.setId(proto_field.getExtend());
                map.put("proto_field", prf);
                List<Proto_field> pfs = protoFieldService.select(map);
                if (pfs.size() <= 0) {
                    result = Config.ERROR;
                    message = "非法的extend操作，不存在指定extend";
                    throw new ControllerException(message);
                }
                map.clear();
                     /*检查proto_id关联的project_id的是否存在一个属于该用户的*/
                Proto pr = new Proto();
                pr.setId(pfs.get(0).getProto_id());
                map.put("proto", pr);
                List<Proto> prs = protoService.select(map);
                map.clear();
                int j;
                for (j = 0; j < prs.size(); j++) {
                    if (projectService.checkProject_id(prs.get(j).getProject_id(), user.getId())) {
                        break;
                    }
                }
                if (j < prs.size()) {
                    result = Config.ERROR;
                    message = "非法跨的extend操作,proto关联的project不合法";
                    throw new ControllerException(message);
                }

            }
            /*数据处理*/

            /*业务操作*/
            protoFieldService.insert(proto_field);
            result = Config.SUCCESS;
            map.put(Config.RESULT, result);

        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, m.getMessage());
        } finally {
            return map;
        }
    }

    /*获取修改页面*/
    @RequestMapping(value = "/proto_field/update")
    public String update(Proto_field proto_field, Model model) {
        model.addAttribute(proto_field);
        return "login/protoField_update.jsp";
    }

    /*处理更新请求*/
    @RequestMapping(value = "/proto_field/update", params = "json")
    public @ResponseBody Map<String, Object> update_json(HttpSession session, Proto_field proto_field) {
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (proto_field.getProto_id() == null || proto_field.getId() == null) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }

            /*权限检验*/
            User user = (User) session.getAttribute("user");
                /*获取该proto_id关联的project_id*/
            Proto p = new Proto();
            p.setId(proto_field.getProto_id());
            map.put("proto", p);
            List<Proto> protos = protoService.select(map);
            map.clear();
            if (protos.size() <= 0) {
                result = Config.ERROR;
                message = "非法的protoId操作";
                throw new ControllerException(message);
            }
                /*检验关联的project_id是否属于该用户（只要有一个关联，就通过）*/
            int i = 0;
            for (i = 0; i < protos.size(); i++) {
                if (projectService.checkProject_id(protos.get(i).getProject_id(), user.getId())) {
                    break;
                }
            }
            if (!(i < protos.size())) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
                /*如果extend字段不为空，就得检验该extend字段是否合法*/
            if (proto_field.getExtend() != null) {
                Proto_field prf = new Proto_field();
                prf.setId(proto_field.getExtend());
                map.put("proto_field", prf);
                List<Proto_field> pfs = protoFieldService.select(map);
                if (pfs.size() <= 0) {
                    result = Config.ERROR;
                    message = "非法的extend操作，不存在指定extend";
                    throw new ControllerException(message);
                }
                map.clear();
                     /*检查proto_id关联的project_id的是否存在一个属于该用户的*/
                Proto pr = new Proto();
                pr.setId(pfs.get(0).getProto_id());
                map.put("proto", pr);
                List<Proto> prs = protoService.select(map);
                map.clear();
                int j;
                for (j = 0; j < prs.size(); j++) {
                    if (projectService.checkProject_id(prs.get(j).getProject_id(), user.getId())) {
                        break;
                    }
                }
                if (j < prs.size()) {
                    result = Config.ERROR;
                    message = "非法跨的extend操作,proto关联的project不合法";
                    throw new ControllerException(message);
                }

            }
            /*数据处理*/

            /*业务操作*/
            protoFieldService.update(proto_field);
            result = Config.SUCCESS;
            map.clear();
            map.put(Config.RESULT, result);


        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, m.getMessage());
        } finally {
            return map;
        }
    }

    /*删除操作*/
    @RequestMapping(value = "/proto_field/delete",params = "json")
    public @ResponseBody Map<String,Object> delete_json(HttpSession session,Proto_field proto_field) {
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if (proto_field.getProto_id() == null || proto_field.getId() == null) {
                result = Config.ERROR;
                message = "存在不能为空的数据输入";
                throw new ControllerException(message);
            }
            /*权限检验*/
            User user = (User) session.getAttribute("user");
                /*获取该proto_id关联的project_id*/
            Proto p = new Proto();
            p.setId(proto_field.getProto_id());
            map.put("proto", p);
            List<Proto> protos = protoService.select(map);
            map.clear();
            if (protos.size() <= 0) {
                result = Config.ERROR;
                message = "非法的protoId操作";
                throw new ControllerException(message);
            }
                /*检验关联的project_id是否属于该用户（只要有一个关联，就通过）*/
            int i = 0;
            for (i = 0; i < protos.size(); i++) {
                if (projectService.checkProject_id(protos.get(i).getProject_id(), user.getId())) {
                    break;
                }
            }
            if (!(i < protos.size())) {
                result = Config.ERROR;
                message = "非法的projectId操作";
                throw new ControllerException(message);
            }
                /*检查该id是否在其他的记录的extend中*/
            Proto_field pfd = new Proto_field();
            pfd.setExtend(proto_field.getId());
            map.put("proto_field", pfd);
            List<Proto_field> pfs = protoFieldService.select(map);
            map.clear();
            if (pfs.size() > 0) {
                for (Proto_field pro :
                        pfs) {
                    if (!(pro.getExtend().equals(proto_field.getId()))) {
                        result = Config.ERROR;
                        message = "存在以该记录为extend的记录依赖，请先删除其他记录";
                        throw new ControllerException(message);
                    }
                }
            }
            /*数据处理*/

            /*业务操作*/
            protoFieldService.delete(proto_field.getId());
            result = Config.SUCCESS;
            map.clear();
            map.put(Config.RESULT, result);

        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, m.getMessage());
        } finally {
            return map;
        }
    }


}
