package com.capslock.sharding.api.rule

import com.capslock.sharding.api.rule.ShardingValue.ShardingValueType

/**
 * Created by capslock1874.
 */
case class ShardingValue[T <: Comparable[AnyVal]](logicTableName: String, columnName: String, value: Option[T], values: Option[List[T]],
    valueRange: Option[Range], shardingValueType: ShardingValueType) {
}

object ShardingValue {

    sealed trait ShardingValueType

    case object SINGLE extends ShardingValueType

    case object LIST extends ShardingValueType

    case object RANGE extends ShardingValueType

    def apply[T <: Comparable[AnyVal]](logicTableName: String, columnName: String, value: T): ShardingValue[T] =
        new ShardingValue[T](logicTableName, columnName, Some(value), None, None, SINGLE)

    def apply[T <: Comparable[AnyVal]](logicTableName: String, columnName: String, values: List[T]): ShardingValue[T] =
        new ShardingValue[T](logicTableName, columnName, None, Some(values), None, LIST)

    def apply[T <: Comparable[AnyVal]](logicTableName: String, columnName: String, valueRange: Range): ShardingValue[T] =
        new ShardingValue[T](logicTableName, columnName, None, None, Some(valueRange), RANGE)
}
