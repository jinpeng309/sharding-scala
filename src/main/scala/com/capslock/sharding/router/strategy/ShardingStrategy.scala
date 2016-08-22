package com.capslock.sharding.router.strategy

import com.capslock.sharding.api.rule.ShardingValue
import com.capslock.sharding.parser.result.router.SQLStatementType

/**
 * Created by capslock1874.
 */
class ShardingStrategy[T <: Comparable[AnyVal]](val shardingColumns: List[String], val shardingAlgorithm: ShardingAlgorithm) {
    def doStaticSharding(sqlStatementType: SQLStatementType.Type, availableTargetNames: List[String],
        shardingValues: List[ShardingValue[T]]): List[String] = {
        if (shardingValues.isEmpty) {
            require(!isInsertMulti(sqlStatementType, availableTargetNames))
            availableTargetNames
        } else {
            List()
        }
    }

    def isInsertMulti(sqlStatementType: SQLStatementType.Type, availableTargetNames: List[String]) =
        sqlStatementType == SQLStatementType.INSERT && availableTargetNames.size > 1

    def doMultiKeySharding(availableTargetNames: List[String], shardingValues: List[ShardingValue[T]]): List[String] = List()

    def doSingleKeySharding(availableTargetNames: List[String], shardingValues: List[ShardingValue[T]]): List[String] = {
        val singleKeyShardingAlgorithm = shardingAlgorithm.asInstanceOf[SingleKeyShardingAlgorithm[T]]
        val shardingValue = shardingValues.head
        shardingValue.shardingValueType match {
            case ShardingValue.SINGLE =>
                List(singleKeyShardingAlgorithm.doEqualSharding(availableTargetNames, shardingValue))
            case ShardingValue.LIST =>
                singleKeyShardingAlgorithm.doInSharding(availableTargetNames, shardingValue)
            case ShardingValue.RANGE =>
                singleKeyShardingAlgorithm.doBetweenSharding(availableTargetNames, shardingValue)
            case _ =>
                List()
        }
    }

    def doSharding(availableTargetNames: List[String],
        shardingValues: List[ShardingValue[T]]): List[String] = {
        shardingAlgorithm match {
            case _: SingleKeyShardingAlgorithm[_] =>
                doSingleKeySharding(availableTargetNames, shardingValues)
            case _: MultipleKeysShardingAlgorithm[_] =>
                doMultiKeySharding(availableTargetNames, shardingValues)
        }
    }
}

object ShardingStrategy extends App {
    printf("name")
}


