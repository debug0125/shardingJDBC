package com.ryf.dbsharding.simple.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
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
    @Insert("insert into t_order(price,user_id,status,create_date)values(#{price},#{userId},#{status},#{date})")
    int insertOrder(@Param("price") BigDecimal price, @Param("userId") Long userId, @Param("status") String status, Date date);

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
            "and user_id = #{userId}"+
            "</script>"})
    List<Map> selectOrderbyIdsAndUserId(@Param("orderIds") List<Long> orderIds, Long userId);

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
            "and user_id in " +
            "<foreach collection='userIds' item='uesrId' open='(' separator=',' close=')'>" +
            " #{uesrId} " +
            "</foreach>"+
            "</script>"})
    List<Map> selectOrderbyIdsAndUserIds(@Param("orderIds") List<Long> orderIds, List<Long> userIds);

    @Select("select * from t_order order by order_id")
    List<Map> selectListOrderByOrderId();

}
