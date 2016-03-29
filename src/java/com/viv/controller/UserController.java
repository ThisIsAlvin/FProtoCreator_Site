package com.viv.controller;

import com.viv.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by viv on 16-3-28.
 */
@Controller
public class UserController {

    private static Map<String,User> users;

    public UserController(){
        if(users==null){
            users =new HashMap<String,User>();
        }
        users.put("viv",new User("viv","man","GD",0111111,"11@11.com"));
    }

    @RequestMapping(value = "/list.do",params = "json")

    public @ResponseBody Map<String,User> list(){
        User u = new User();
        u.setName("viv");
        u.setHome("dd");
        u.setSex("dd");
        users.put("u",u);
        return users;
    }
}
