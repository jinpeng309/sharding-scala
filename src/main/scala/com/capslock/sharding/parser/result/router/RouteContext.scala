package com.capslock.sharding.parser.result.router

/**
 * Created by capslock1874.
 */
case class RouteContext(tables: Set[Table], sQLStatementType: SQLStatementType.Type, sqlBuilder: SQLBuilder)
