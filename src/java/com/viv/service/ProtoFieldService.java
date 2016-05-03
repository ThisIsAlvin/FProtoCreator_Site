package com.viv.service;

import com.viv.dao.ProtoFieldOperation;
import com.viv.dao.ProtoOperation;
import com.viv.entity.Proto;
import com.viv.entity.Proto_field;
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
public class ProtoFieldService {
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


    /*动态条件查询*/
    public List<Proto_field> select(Map map) {
        SqlSession session = sessionFactory.openSession();
        try {
            /*数据处理*/
            if (map.containsKey("like") && map.containsKey("proto_field")) {
                Proto_field proto_field = (Proto_field) map.get("proto_field");
                if (proto_field.getName() != null) {
                    proto_field.setName("%"+proto_field.getName()+"%");
                }
                if (proto_field.getRemarks() != null) {
                    proto_field.setRemarks("%"+proto_field.getRemarks()+"%");
                }
            }

            ProtoFieldOperation protoFieldOperation = session.getMapper(ProtoFieldOperation.class);

            List<Proto_field> proto_fields = protoFieldOperation.select(map);

            session.commit();
            return proto_fields;
        }finally {
            session.close();
        }
    }



}
