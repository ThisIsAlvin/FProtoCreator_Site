package com.viv.service;

import com.viv.dao.UserOperation;
import com.viv.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import sun.plugin.util.UserProfile;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

/**
 * Created by viv on 16-4-20.
 */
public class UserService {
    private static String resource = "conf.xml";
    private static Reader reader;
    private static SqlSessionFactory sessionFactory;

    static{
        try {
            reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSession(){
        return sessionFactory;
    }

    /*添加用户*/
    public void insert(User user){
        SqlSession session = sessionFactory.openSession();
        try{
            UserOperation userOperation = session.getMapper(UserOperation.class);
            userOperation.insert(user);
            session.commit();
        }finally {
            session.close();
        }

    }
    /*删除用户*/
    public void delete(int id){
        SqlSession session = sessionFactory.openSession();
        try{
            UserOperation userOperation = session.getMapper(UserOperation.class);
            userOperation.delete(id);
            session.commit();
        }finally {
            session.close();
        }

    }
    /*修改用户*/
    public void update(User user){

        SqlSession session = sessionFactory.openSession();
        try {
            UserOperation userOperation = session.getMapper(UserOperation.class);
            userOperation.update(user);
            session.commit();
        }finally {
            session.close();
        }
    }
    /*登录时验证用户*/
    public User selectBy_username_password(String username,String password){
        SqlSession session = sessionFactory.openSession();
        User user = null;
        try {
            UserOperation userOperation = session.getMapper(UserOperation.class);
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
             user = userOperation.selectByUsernamePassword(u);
            session.commit();
        }finally {
            session.close();
        }
        return user;
    }

    /*注册时检验用户是否存在 */
    public List<User> selectBy_username(String username){
        SqlSession session = sessionFactory.openSession();
        List<User> users = null;
        try {
            UserOperation userOperation = session.getMapper(UserOperation.class);
            users = userOperation.selectByUsername(username);
            session.commit();
        }finally {
            session.close();
        }
        return users;
    }


}
