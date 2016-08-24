package com.capslock.sharding.parser.result.merger

/**
 * Created by capslock1874.
 */
class OrderByColumn(owner: Option[String], name: Option[String], alias: Option[String], orderType: OrderByColumn.OrderByType)
    extends AbstractSortableColumn(owner, name, alias, orderType) with IndexColumn {
    private var columnIndex: Int = -1
    private var index: Option[Int] = Option.empty

    override def setColumnIndex(index: Int): Unit = {
        if (this.index.isEmpty) {
            columnIndex = index
        }
    }

    override def getColumnIndex: Int = columnIndex

    override def getColumnLabel: Option[String] = alias

    override def getColumnName: Option[String] = name
}

object OrderByColumn {

    sealed trait OrderByType

    case object ASC extends OrderByType

    case object DESC extends OrderByType

}