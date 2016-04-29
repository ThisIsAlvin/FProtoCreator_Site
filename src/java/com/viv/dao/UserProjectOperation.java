package com.viv.dao;

import com.viv.entity.User_project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viv on 16-4-24.
 */
public interface UserProjectOperation {

    /*添加一条记录*/
    public void insert(Map map);
    /*根据userId和projectId确定，删除一个UserProject记录*/
    public void delete(Map map);
    /*根据userId和project确定，选择一个userProject记录*/
    public List<User_project> selectByUseridProjectid(Map map);
    /*根据UserId返回记录数量*/
    public int countByUserId(int user_id);

}
