package com.viv.dao;

import com.viv.entity.Project_info;
import com.viv.entity.User_project;

import java.util.List;

/**
 * Created by viv on 16-4-23.
 */
public interface ProjectInfoOperation {

    /*添加一个实体*/
    public void insert(Project_info project_info);
    /*根据id删除一个实体*/
    public void delete(Integer id);
    /*根据id修改一个实体*/
    public void update(Project_info project_info);
    /*根据id查询一个实体*/
    public Project_info selectById(Integer id);
    /*根据userId查询该用户所有的project_info实体*/
    public List<User_project> selectByUserId(int userId);

}
