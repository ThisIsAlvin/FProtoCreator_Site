package com.viv.controller;

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
        String result = "error";
        String message = "error";
        Map<String,Object> map = new HashMap<String, Object>();
        if(user.getUsername().equals("")||user.getUsername()==null||user.getPassword().equals("")||user.getPassword()==null){
            message = "登录信息不全";
            map.put("result",result);
            map.put("message",message);
            return map;
        }
        User u = userService.selectBy_username_password(user.getUsername(),user.getPassword());
        if(u!=null){
//            登录操作
            session.setAttribute("user",u);
            result = "success";
            map.put("result",result);
            map.put("user",u);
            return map;
        }
        message ="未知错误";
        map.put("result",result);
        map.put("message",message);
        return map;
    }
    /*显示注册页面*/
    @RequestMapping(value = "register")
    public String register(){
        return "index/register.html";
    }
    /*处理注册请求*/
    @RequestMapping(value = "register",params = "json")
    public @ResponseBody Map<String,Object> register_json(User user){
        String result = "error";
        String message = "error";
        Map<String,Object> map = new HashMap<String, Object>();
        if(user.getName().isEmpty()||user.getPassword().isEmpty()||user.getUsername().isEmpty()){
            message="注册信息不全";
            map.put("result",result);
            map.put("message",message);
            return map;
        }
        List<User> u = userService.selectBy_username(user.getUsername());
        if(u.size()!=0){
            message="用户名已注册";
            map.put("result",result);
            map.put("message",message);
            return map;
        }
        User us = new User();
        us.setUsername(user.getUsername());
        us.setName(user.getName());
        us.setPassword(user.getPassword());
        userService.insert(us);
        result="success";
        message="注册成功";
        map.put("result",result);
        map.put("message",message);
        return map;


    }

}
