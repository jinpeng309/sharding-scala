package com.capslock.sharding.parser.result.router

import com.capslock.sharding.parser.result.router.Condition.{BinaryOperator, Column}

/**
 * Created by capslock1874.
 */
class ConditionContext {
    var conditions = Map[Column, Condition]()

    def add(condition: Condition): Unit = conditions += (condition.column -> condition)

    def find(table: String, column: String): Option[Condition] = conditions.get(Column(column, table))

    def find(table: String, column: String, operator: BinaryOperator): Option[Condition] =
        find(table, column).filter(condition => condition.operator.eq(operator))

    def isEmpty: Boolean = conditions.isEmpty

    def clear(): Unit = conditions = Map[Column, Condition]()

    def getAll: Iterable[Condition] = conditions.values
}
