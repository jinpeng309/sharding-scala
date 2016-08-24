package com.capslock.sharding.parser.result.router

/**
 * Created by capslock1874.
 */
class RouteContext {
    var tables: Set[Table] = Set()
    var sqlStatementType: SQLStatementType.Type = SQLStatementType.UNKNOWN
    var sqlBuilder: SQLBuilder = new SQLBuilder()

    def addTable(table: Table): Unit = {
        tables += table
    }
}
