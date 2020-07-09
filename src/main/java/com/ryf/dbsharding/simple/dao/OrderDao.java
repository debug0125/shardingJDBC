package com.ryf.dbsharding.simple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * create by ryf on 2020-07-09 14:37
 */
@Mapper
@Component
public interface OrderDao {

    /**
     * 新增订单
     * @param price
     * @param userId
     * @param status
     * @return
     */
    @Insert("insert into t_order(price,user_id,status)values(#{price},#{userId},#{status})")
    int insertOrder(@Param("price") BigDecimal price, @Param("userId") Long userId, @Param("status") String status);

    /**
     * 查询订单
     * @param orderIds
     * @return
     */
    @Select({"<script>" +
            "select " +
            " * " +
            " from t_order t" +
            " where t.order_id in " +
            "<foreach collection='orderIds' item='id' open='(' separator=',' close=')'>" +
            " #{id} " +
            "</foreach>"+
            "</script>"})
    List<Map> selectOrderbyIds(@Param("orderIds") List<Long> orderIds);

    @Select("select * from t_order order by order_id")
    List<Map> selectListOrderByOrderId();

}
