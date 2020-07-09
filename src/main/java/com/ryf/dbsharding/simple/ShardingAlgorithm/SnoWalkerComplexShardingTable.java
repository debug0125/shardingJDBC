package com.ryf.dbsharding.simple.ShardingAlgorithm;

import com.ryf.dbsharding.simple.Enum.DbAndTableEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * create by ryf on 2020-07-08 16:29
 */
@Slf4j
public class SnoWalkerComplexShardingTable implements ComplexKeysShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
//        log.info("collection----=====-------->>>" + JSON.toJSONString(collection));
//        log.info("collection----=====-------->>>" + JSON.toJSONString(complexKeysShardingValue));
        List<String> shardingResults = new ArrayList<>();
        // 表名前缀
        String logicTableName = complexKeysShardingValue.getLogicTableName();
        // 分片规则所需字段
        Map<String, Collection<Long>> columnsMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        Long orderId = (Long) columnsMap.get("order_id").toArray()[0];
        Long index = getIndex(orderId,collection.size());
        String tableName = logicTableName+"_"+index;

        shardingResults.add(tableName);
        return shardingResults;
    }

    public Long getIndex (Long orderId, Integer dbSize) {
        Integer tableSize = DbAndTableEnum.T_ORDER.getTableSize();
        return orderId % (dbSize* tableSize) % tableSize + 1;
    }

}
