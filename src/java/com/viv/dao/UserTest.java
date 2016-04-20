package com.viv.dao;

import com.viv.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.Reader;


/**
 * Created by viv on 16-4-4.
 */

public class UserTest {

//    public static void main(String[] args) throws IOException {
//        String resource = "conf.xml";
//        InputStream is = UserTest.class.getClassLoader().getResourceAsStream(resource);
//
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
//
//        SqlSession session = factory.openSession();
//
//        String statement = "com.viv.dao.userMapper.getUser";
//
//        User user = session.selectOne(statement, "a");
//        System.out.print(user);
//    }

    public void main() throws IOException {
        String resource = "conf.xml";
//加载 mybatis 的配置文件(它也加载关联的映射文件)
        Reader reader = Resources.getResourceAsReader(resource);
//构建 sqlSession 的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
//创建能执行映射文件中 sql 的 sqlSession
        SqlSession session = sessionFactory.openSession();
//映射 sql 的标识字符串
        String statement = "com.viv.dao.userMapper.getUser";
//执行查询返回一个唯一 user 对象的 sql
        User user = session.selectOne(statement, "a");
        System.out.println(user);
    }


    @Test
    public void test(){
        System.out.println("dsdfasdffadfaf=========================");
    }


}
