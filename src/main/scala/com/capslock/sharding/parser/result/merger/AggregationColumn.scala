package com.capslock.sharding.parser.result.merger

import com.capslock.sharding.parser.result.merger.AggregationColumn.AggregationType

/**
 * Created by capslock1874.
 */
class AggregationColumn(val express: String, val aggregationType: AggregationType, val alias: Option[String],
    val option: Option[String], val derivedColumns: List[AggregationColumn]) extends IndexColumn {
    private var columnIndex = -1

    override def setColumnIndex(index: Int): Unit = columnIndex = index

    override def getColumnIndex: Int = columnIndex

    override def getColumnLabel: Option[String] = alias

    override def getColumnName: Option[String] = Some(express)
}

object AggregationColumn {

    sealed trait AggregationType

    case object MAX extends AggregationType

    case object MIN extends AggregationType

    case object COUNT extends AggregationType

    case object SUM extends AggregationType

    case object AVG extends AggregationType

}