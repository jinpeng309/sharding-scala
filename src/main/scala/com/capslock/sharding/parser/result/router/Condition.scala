package com.capslock.sharding.parser.result.router

import com.capslock.sharding.parser.result.router.Condition.{BinaryOperator, Column}

/**
 * Created by capslock1874.
 */
case class Condition(column: Column, operator: BinaryOperator, values: List[Comparable[Any]], valueIndices: List[Int])

object Condition {

    case class Column(columnName: String, tableName: String)

    sealed class BinaryOperator(express: String)

    case object EQUAL extends BinaryOperator("=")

    case object IN extends BinaryOperator("IN")

    case object BETWEEN extends BinaryOperator("BETWEEN")

}