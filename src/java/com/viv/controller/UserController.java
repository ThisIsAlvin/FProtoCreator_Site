package com.viv.controller;

import com.viv.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by viv on 16-4-23.
 */
@Controller
@RequestMapping(value = "/login")
public class UserController {
    private UserService userService;
    public UserController(){
        userService = new UserService();
    }
    /*显示主页页面，合法session*/
    @RequestMapping(value = "/user/welcome")
    public String index(){
        return "login/myIndex.html";
    }

}
