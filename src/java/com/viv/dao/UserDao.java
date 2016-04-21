package com.viv.dao;

import com.viv.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

/**
 * Created by viv on 16-4-20.
 */
public class UserDao {

    String resource = "conf.xml";
    Reader reader;
    SqlSessionFactory sessionFactory;

    public UserDao() {
        try {
            reader = getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*添加用户*/
    public void insert(User user){

        SqlSession sqlSession = sessionFactory.openSession();

        String statement = "com.viv.dao.userMapper.insert";

        int insert = sqlSession.insert(statement, user);

        sqlSession.commit();

        System.out.println("insert:"+insert);

        sqlSession.close();



    }
    /*删除用户*/
    public void delete(int id){

        SqlSession sqlSession = sessionFactory.openSession();

        String statement = "com.viv.dao.userMapper.delete";

        int delete = sqlSession.delete(statement, id);

        sqlSession.commit();

        System.out.println("delete:"+delete);

        sqlSession.close();


    }
    /*修改用户*/
    public void update(User user){

        SqlSession sqlSession = sessionFactory.openSession();

        String statement = "com.viv.dao.userMapper.update";

        int update = sqlSession.update(statement, user);

        sqlSession.commit();

        System.out.println("update"+update);

        sqlSession.close();


    }
    /*登录时验证用户*/
    public List<User> selectBy_username_password(String username,String password){

        SqlSession sqlSession = sessionFactory.openSession();

        String statement = "com.viv.dao.userMapper.select_by_username_password";

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        List<User> users = sqlSession.selectList(statement,user);

        sqlSession.commit();

        System.out.println(users);

        sqlSession.close();

        return users;
    }

    /*注册时检验用户是否存在 */
    public List<User> selectBy_username(String username){

        SqlSession sqlSession = sessionFactory.openSession();

        String statement = "com.viv.dao.userMapper.select_by_username";

        User user = new User();
        user.setUsername(username);

        List<User> users = sqlSession.selectList(statement,user);

        sqlSession.commit();

        System.out.println(users);

        sqlSession.close();

        return users;
    }


}
