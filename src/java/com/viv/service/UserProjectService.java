package com.viv.service;

import com.viv.dao.ProjectInfoOperation;
import com.viv.dao.UserProjectOperation;
import com.viv.entity.Page;
import com.viv.entity.Project_info;
import com.viv.entity.User_project;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viv on 16-4-23.
 */
public class UserProjectService {
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

    /*根据userId，返回记录数量*/
    public int countByUserId(Long user_id) {
        SqlSession session = sessionFactory.openSession();
        try{
            UserProjectOperation userProjectOperation = session.getMapper(UserProjectOperation.class);

            int count = userProjectOperation.countByUserId(user_id);

            session.commit();
            return count;
        }finally {
            session.close();
        }
    }
}
