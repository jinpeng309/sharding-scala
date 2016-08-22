package com.capslock.sharding.api.rule

/**
 * Created by capslock1874.
 */
case class BindingTableRule(tableRules: List[TableRule]) {
    def hasLogicTable(logicTableName: String): Boolean = tableRules.exists(table => table.logicTable.eq(logicTableName))

    def containsAtLeastOneLogicTable(logicTableNames: Set[String]): Boolean =
        tableRules.exists(table => logicTableNames.contains(table.logicTable))

    def getBindingActualTable(dataSource: String, logicTable: String, otherActualTableName: String): Option[String] = {
        val index = tableRules
            .find(tableRule => tableRule.findActualTableIndex(dataSource, otherActualTableName) != -1)
            .map(tableRule => tableRule.findActualTableIndex(dataSource, otherActualTableName))
            .getOrElse(-1)
        tableRules.find(tableRule => tableRule.logicTable.eq(logicTable))
            .map(table => table.getActualDatasourceNames.apply(index))
    }

    def getAllLogicTables = tableRules.map(tableRule => tableRule.logicTable)
}
