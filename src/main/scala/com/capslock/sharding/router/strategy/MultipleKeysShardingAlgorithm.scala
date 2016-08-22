package com.capslock.sharding.router.strategy

import com.capslock.sharding.api.rule.ShardingValue

/**
 * Created by capslock1874.
 */
trait MultipleKeysShardingAlgorithm[T <: Comparable[Any]] extends ShardingAlgorithm {
    def doSharding(availableTargetNames: List[String], shardingValue: ShardingValue[T]): List[String]
}
