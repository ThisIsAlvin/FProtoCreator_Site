package com.viv.entity;

/**
 * Created by viv on 16-4-30.
 */
public class Proto_field {
    private Long id;
    private Long proto_id;
    private String name;
    private Integer type;
    private Long extend;
    private Integer is_array;
    private String remarks;

    public Long getExtend() {
        return extend;
    }

    public void setExtend(Long extend) {
        this.extend = extend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIs_array() {
        return is_array;
    }

    public void setIs_array(Integer is_array) {
        this.is_array = is_array;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProto_id() {
        return proto_id;
    }

    public void setProto_id(Long proto_id) {
        this.proto_id = proto_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
