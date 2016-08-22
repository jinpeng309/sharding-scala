package com.capslock.sharding.router.strategy

import com.capslock.sharding.api.rule.ShardingValue

/**
 * Created by capslock1874.
 */
trait SingleKeyShardingAlgorithm[T <: Comparable[AnyVal]] extends ShardingAlgorithm {
    def doEqualSharding(availableTargetNames: List[String], shardingValue: ShardingValue[T]): String

    def doInSharding(availableTargetNames: List[String], shardingValue: ShardingValue[T]): List[String]

    def doBetweenSharding(availableTargetNames: List[String], shardingValue: ShardingValue[T]): List[String]
}
