package com.viv.dao;

import com.viv.entity.Proto;

import java.util.List;
import java.util.Map;

/**
 * Created by viv on 16-4-30.
 */
public interface ProtoOperation {
    /*添加一个实体*/
    public void insert(Proto proto);

    /*删除一个实体*/
    public void delete(Long id);

    /*修改一个实体*/
    public void updateBy(Proto proto);

    /*根据id查询一个实体*/
    public Proto selectById(Long id);

    /*根据project_id查询实体*/
    public List<Proto> selectByProjectId(Long project_id);

    /*根据project_id，返回记录数量*/
    public int countByProjectId(Long project_id);

    /*动态查询*/
    public List<Proto> select(Map map);

    /*动态更新*/
    public int update(Proto proto);

}
