package com.capslock.sharding.parser.result.merger

/**
 * Created by capslock1874.
 */
abstract class AbstractSortableColumn(val owner: Option[String], val name: Option[String], val alias: Option[String],
    val orderType: OrderByColumn.OrderByType)
