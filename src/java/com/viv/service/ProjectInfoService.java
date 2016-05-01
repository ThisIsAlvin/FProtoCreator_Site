package com.viv.service;

import com.viv.dao.ProjectInfoOperation;
import com.viv.dao.UserProjectOperation;
import com.viv.entity.Page;
import com.viv.entity.Project_info;
import com.viv.entity.User;
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
public class ProjectInfoService {
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
    /*向project_info添加一条记录,同时向user_project添加一条记录*/
    public void insert(Project_info project_info ,Long userId){
        SqlSession session = sessionFactory.openSession();
        try {
            ProjectInfoOperation projectInfoOperation = session.getMapper(ProjectInfoOperation.class);
            projectInfoOperation.insert(project_info);
            UserProjectOperation userProjectOperation = session.getMapper(UserProjectOperation.class);
            Map map = new HashMap();
            map.put("user_id",userId);
            map.put("project_id",project_info.getId());
            userProjectOperation.insert(map);
            session.commit();
        }finally {
            session.close();
        }
    }
    /*根据id删除一条记录,以及与这条记录关联的User_project记录*/
    public void delete(Integer projectId , Long userId){
        SqlSession  session = sessionFactory.openSession();
        try{
            ProjectInfoOperation projectInfoOperation = session.getMapper(ProjectInfoOperation.class);
            projectInfoOperation.delete(projectId);
            UserProjectOperation userProjectOperation = session.getMapper(UserProjectOperation.class);
            Map map = new HashMap();
            map.put("user_id",userId);
            map.put("project_id",projectId);
            userProjectOperation.delete(map);
            session.commit();
        }finally {
            session.close();
        }
    }
    /*根据id选择修改一个实体,需要验证该实体是否是该用户的*/
    public void update(Project_info project_info,Long userId){
        SqlSession session = sessionFactory.openSession();
        try{
            UserProjectOperation userProjectOperation = session.getMapper(UserProjectOperation.class);
            Map map = new HashMap();
            map.put("user_id",userId);
            map.put("project_id",project_info.getId());
            List<User_project> projects = userProjectOperation.selectByUseridProjectid(map);
            if(projects.size()>=1){
                ProjectInfoOperation projectInfoOperation = session.getMapper(ProjectInfoOperation.class);
                projectInfoOperation.update(project_info);
            }
            session.commit();
        }finally {
            session.close();
        }
    }

    /*检验一个project_id是否和一个user_id处于同一条记录*/
    public boolean checkProject_id(Long project_id,Long user_id) {
        SqlSession session = sessionFactory.openSession();
        try{
            UserProjectOperation userProjectOperation = session.getMapper(UserProjectOperation.class);
            Map map = new HashMap();
            map.put("user_id",user_id);
            map.put("project_id",project_id);
            List<User_project> projects = userProjectOperation.selectByUseridProjectid(map);
            session.commit();
            if(projects.size()>=1){
                return true;
            }
            return false;
        }finally {
            session.close();
        }
    }

    /*根据id查询一个实体*/
    public Project_info selectById(Integer id){
        SqlSession session = sessionFactory.openSession();
        try{
            ProjectInfoOperation projectInfoOperation = session.getMapper(ProjectInfoOperation.class);
            Project_info project_info = projectInfoOperation.selectById(id);
            session.commit();
            return project_info;
        }finally {
            session.close();
        }
    }
    /*根据传入的userId查询该用户所有的project_info实体*/
    public List<User_project> selectByUserId(int userId){
        SqlSession session = sessionFactory.openSession();
        try{
            ProjectInfoOperation projectInfoOperation = session.getMapper(ProjectInfoOperation.class);
            List<User_project> user_projects = projectInfoOperation.selectByUserId(userId);
            session.commit();
            return user_projects;
        }finally {
            session.close();
        }
    }

     /*根据userId以及分页信息，分页查询该用户所有的project_info实体*/
    public List<User_project> selectByUserId_page(Page page,Long userId) {
        SqlSession session = sessionFactory.openSession();
        try{
            ProjectInfoOperation projectInfoOperation = session.getMapper(ProjectInfoOperation.class);
            Map<String,Object> map = new HashMap<>();
            map.put("id",userId);
            map.put("page",page);
            List<User_project> user_projects = projectInfoOperation.selectByUserId_page(map);
            session.commit();
            return user_projects;
        }finally {
            session.close();
        }

    }
}
