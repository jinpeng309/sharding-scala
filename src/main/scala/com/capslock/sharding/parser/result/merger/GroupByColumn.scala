package com.capslock.sharding.parser.result.merger

/**
 * Created by capslock1874.
 */
class GroupByColumn(owner: Option[String], name: Option[String], alias: Option[String], orderType: OrderByColumn.OrderByType)
    extends AbstractSortableColumn(owner, name, alias, orderType) with IndexColumn {
    private var columnIndex: Int = -1

    override def setColumnIndex(index: Int): Unit = {
        columnIndex = index
    }

    override def getColumnIndex: Int = columnIndex

    override def getColumnLabel: Option[String] = alias

    override def getColumnName: Option[String] = name
}
