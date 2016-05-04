package com.viv.dao;

import com.viv.entity.Page;
import com.viv.entity.Project_info;
import com.viv.entity.User;
import com.viv.entity.User_project;

import java.util.List;
import java.util.Map;

/**
 * Created by viv on 16-4-23.
 */
public interface ProjectInfoOperation {

    /*添加一个实体*/
    public void insert(Project_info project_info);

    /*根据id删除一个实体*/
    public void delete(Long id);

    /*动态更新*/
    public void update(Project_info project_info);

    /*根据id查询一个实体*/
    public Project_info selectById(Integer id);

    /*根据userId查询该用户所有的project_info实体*/
    public List<User_project> selectByUserId(int userId);

    /*根据userId以及分页信息，分页查询该用户所有的project_info实体*/
    public List<User_project> selectByUserId_page(Map map);

    /*动态分页查询 like==null 精确查询   like ！= null 模糊查询*/
    public List<User_project> select(Map map);

}
