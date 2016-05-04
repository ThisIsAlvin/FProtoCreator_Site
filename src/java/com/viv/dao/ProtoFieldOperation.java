package com.viv.dao;

import com.viv.entity.Proto_field;

import java.util.List;
import java.util.Map;

/**
 * Created by viv on 16-4-30.
 */
public interface ProtoFieldOperation {

    /*分页动态查询 like == null 精确查询 like ！= null 模糊查询*/
    public List<Proto_field> select(Map map);

    /*添加一个实体*/
    public void insert(Proto_field proto_field);

    /*动态更新*/
    public void update(Proto_field proto_field);

    /*删除一个实体*/
    public void delete(Long id);
}
