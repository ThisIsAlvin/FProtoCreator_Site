package com.viv.controller;

import com.viv.Config;
import com.viv.exception.ControllerException;
import com.viv.service.UserService;
import com.viv.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viv on 16-4-21.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {
    private UserService userService;

    public IndexController(){
        userService = new UserService();
    }

    /*显示登录页面*/
    @RequestMapping(value = "login")
    public String login(){
        return "index/login.html";
    }
    /*处理登录请求*/
    @RequestMapping(value = "login",params = "json")
    public @ResponseBody Map<String,Object> login_json(User user,HttpSession session){
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if(user.getUsername().trim().equals("")||user.getUsername()==null||user.getPassword().trim().equals("")||user.getPassword()==null){
                result = Config.ERROR;
                message = "登录信息不全";
                throw new ControllerException(message);
            }
            /*权限检验*/
            User u = new User();
            u.setUsername(user.getUsername());
            u.setPassword(user.getPassword());
            map.put("user", u);
            List<User> users = userService.select(map);
            map.clear();
            if (users.size() < 1) {
                result = Config.ERROR;
                message = "登录信息错误";
                throw new ControllerException(message);
            }
            /*数据处理*/

            /*业务操作*/
            session.setAttribute("user",users.get(0));
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);
        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, m.getMessage());
        }finally {
            return map;
        }
    }
    /*显示注册页面*/
    @RequestMapping(value = "register")
    public String register(){
        return "index/register.html";
    }
    /*处理注册请求*/
    @RequestMapping(value = "register",params = "json")
    public @ResponseBody Map<String,Object> register_json(User user){
        Map<String, Object> map = new HashMap<>();
        String result = Config.ERROR;
        String message = Config.ERROR;
        try {
            /*数据检验*/
            if(user.getName() == null || user.getPassword() == null || user.getUsername() == null || user.getName().trim().equals("") || user.getUsername().trim().equals("") || user.getPassword().trim().equals("")){
                result = Config.ERROR;
                message="注册信息不全";
                throw new ControllerException(message);
            }
            /*权限检验*/
                /*检查username是否已用*/
            User us = new User();
            us.setUsername(user.getUsername());
            map.put("user", us);
            List<User> users = userService.select(map);
            map.clear();
            if (users.size() > 1) {
                result = Config.ERROR;
                message = "用户名已注册";
                throw new ControllerException(message);
            }
            /*数据处理*/

            /*业务操作*/
            userService.insert(user);
            result = Config.SUCCESS;
            map.put(Config.RESULT,result);
        } catch (ControllerException m) {
            map.clear();
            map.put(Config.RESULT, result);
            map.put(Config.MESSAGE, m.getMessage());
        }finally {
            return map;
        }
    }

}
