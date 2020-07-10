package com.ryf.dbsharding.simple.ShardingAlgorithm;

import com.alibaba.fastjson.JSON;
import com.ryf.dbsharding.simple.Enum.DbAndTableEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * create by ryf on 2020-07-08 16:29
 */
@Slf4j
public class SnoWalkerComplexShardingDB implements ComplexKeysShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        Collection<String> shardingResults = new ArrayList<>();
        Map<String, Collection<Long>> columnsMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        List userIds = Arrays.asList(columnsMap.get("user_id").toArray());
        for (Object userId:userIds) {
            Long index = getIndex((Long) userId);
            //循环匹配数据表源
            for (String availableTargetName : collection){
                if (availableTargetName.endsWith(index.toString())) {
                    shardingResults.add(availableTargetName);
                    break;
                }
            }
            //匹配到一种路由规则就可以退出
//            if (shardingResults.size() > 0) {
//                break;
//            }
        }
        return shardingResults;
    }

    public Long getIndex (Long userId) {
        return userId % 2 + 1;
    }

}
