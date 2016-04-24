package com.viv.dao;

import com.viv.entity.User;

import java.util.List;

/**
 * Created by viv on 16-4-23.
 */
public interface UserOperation {
    /*添加实体*/
    public void insert(User user);
    /*根据id删除实体*/
    public void delete(int id);
    /*修改实体*/
    public void update(User user);
    /*登录时，根据username和password判断用户是否存在*/
    public User selectByUsernamePassword(User user);
    /*注册时，根据username判断用户是否存在*/
    public List<User> selectByUsername(String username);
}
