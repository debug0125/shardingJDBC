package com.ryf.dbsharding.simple.Enum;

/**
 * create by ryf on 2020-07-09 14:54
 */
public enum DbAndTableEnum {

    T_ORDER(2, 3, "用户数据表枚举");


    /**该表总共分库数量*/
    private Integer dbSize;
    /**该表每个库存在表数量*/
    private Integer tableSize;
    /**描述*/
    private String desc;

    DbAndTableEnum(Integer dbSize, Integer tableSize, String desc) {
        this.dbSize = dbSize;
        this.tableSize = tableSize;
        this.desc = desc;
    }

    public Integer getDbSize() {
        return dbSize;
    }

    public Integer getTableSize() {
        return tableSize;
    }

    public String getDesc() {
        return desc;
    }
}
