package com.viv.service;

import com.viv.dao.ProjectInfoOperation;
import com.viv.dao.ProtoOperation;
import com.viv.entity.Proto;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * Created by viv on 16-4-30.
 */
public class ProtoService {
    private static String resource = "conf.xml";
    private static Reader reader;
    private static SqlSessionFactory sessionFactory;

    static {
        try {
            reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sessionFactory;
    }

    /*添加一个实体*/
    public void insert(Proto proto) {
        SqlSession session = sessionFactory.openSession();
        try {
            ProtoOperation protoOperation = session.getMapper(ProtoOperation.class);

            protoOperation.insert(proto);

            session.commit();
        } finally {
            session.close();
        }
    }

    /*删除一个实体*/
    public void delete(Long id) {
        SqlSession session = sessionFactory.openSession();
        try {
            ProtoOperation protoOperation = session.getMapper(ProtoOperation.class);

            protoOperation.delete(id);

            session.commit();
        } finally {
            session.close();
        }
    }

    /*修改一个实体*/
    public void update(Proto proto) {
        SqlSession session = sessionFactory.openSession();
        try {
            ProtoOperation protoOperation = session.getMapper(ProtoOperation.class);

            protoOperation.update(proto);

            session.commit();
        } finally {
            session.close();
        }
    }

    /*根据id查询一个实体*/
    public Proto selectById(Long id) {
        SqlSession session = sessionFactory.openSession();
        try {
            ProtoOperation protoOperation = session.getMapper(ProtoOperation.class);

            Proto proto = protoOperation.selectById(id);

            session.commit();

            return proto;
        } finally {
            session.close();
        }
    }

    /*根据project_id查询实体*/
    public List<Proto> selectByProjectId(Long project_id) {
        SqlSession session = sessionFactory.openSession();
        try {
            ProtoOperation protoOperation = session.getMapper(ProtoOperation.class);

            List<Proto> protos = protoOperation.selectByProjectId(project_id);

            session.commit();

            return protos;
        } finally {
            session.close();
        }
    }

    /*根据project_id，返回记录数量*/
    public int countByProjectId(Long project_id) {
        SqlSession session = sessionFactory.openSession();
        try {
            ProtoOperation protoOperation = session.getMapper(ProtoOperation.class);

            int count = protoOperation.countByProjectId(project_id);

            session.commit();
            return count;
        }finally {
            session.close();
        }
    }

    /*动态条件查询*/
    public List<Proto> select(Map map) {
        SqlSession session = sessionFactory.openSession();
        try {
            ProtoOperation protoOperation = session.getMapper(ProtoOperation.class);

            List<Proto> protos = protoOperation.select(map);

            session.commit();
            return protos;
        }finally {
            session.close();
        }
    }

}
