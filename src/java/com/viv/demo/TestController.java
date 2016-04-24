package com.viv.demo;

import com.viv.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by viv on 16-3-28.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    private static Map<String,User> users;

    public TestController(){
        if(users==null){
            users =new HashMap<String,User>();
        }
        /*users.put("viv1",new User("viv1","man1","GD1",0111111,"11@11.com"));
        users.put("viv2",new User("viv2","man2","GD2",0111112,"12@11.com"));
        users.put("viv3",new User("viv3","man3","GD3",0111113,"13@11.com"));
        users.put("viv4",new User("viv4","man4","GD4",0111114,"14@11.com"));*/
    }

    /*显示list页面*/
    @RequestMapping(value = "/list")
    public String list(){
        return "demo/list.jsp";
    }

    /*请求users数据*/
    @RequestMapping(value = "/list",params = "json")
    public @ResponseBody Map<String,User> list_json(){

        return users;
    }

    /*显示添加user页面*/
    @RequestMapping(value = "add")
    public String add(){
        return "demo/add.jsp";
    }

    /*处理添加user操作*/
    @RequestMapping(value = "add",params = "json")
    public @ResponseBody String add_json(User user){
        String result = "error";
        users.put(user.getName(),user);
        result = "ok";
        return result;
    }

    /*删除指定userc操作*/
    @RequestMapping(value = "/del",params = "json")
    public @ResponseBody String del(String name){
        String result = "error";
        users.remove(name);
        result = "ok";
        return result;
    }

    /*显示修改订制user数据页面*/
    @RequestMapping(value = "/update")
    public String update(User user, Model model){
        model.addAttribute(user);
        return "demo/update.jsp";
    }

    /*修改订制user数据操作*/
    @RequestMapping(value = "update",params = "json")
    public @ResponseBody String update_json(User user,String oldName){
        String result = "error";
        users.remove(oldName);
        users.put(user.getName(),user);
        result = "ok";
        return result;

    }

}
