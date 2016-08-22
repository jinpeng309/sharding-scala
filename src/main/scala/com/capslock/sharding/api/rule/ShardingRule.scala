package com.capslock.sharding.api.rule

/**
 * Created by capslock1874.
 */
class ShardingRule(val dataSourceRule: DataSourceRule, val tableRules: List[TableRule], val bindingTableRules: List[BindingTableRule],
    databaseShardingStrategy: DatabaseShardingStrategy, tableShardingStrategy: TableShardingStrategy) {

    def findTableRule(logicTableName: String): Option[TableRule] =
        tableRules.find(tableRule => tableRule.logicTable.eq(logicTableName))

    def findBindingTableRule(logicTable: String): Option[BindingTableRule] =
        bindingTableRules.find(bindingTableRule => bindingTableRule.hasLogicTable(logicTable))

    def getTableShardingStrategy(tableRule: TableRule): TableShardingStrategy =
        tableRule.tableShardingStrategy.getOrElse(tableShardingStrategy)

    def getDatabaseShardingStrategy(tableRule: TableRule): DatabaseShardingStrategy =
        tableRule.databaseShardingStrategy.getOrElse(databaseShardingStrategy)

    def findBindingTableRule(logicTables: List[String]): Option[BindingTableRule] =
        bindingTableRules.find(bindingTableRule => bindingTableRule.containsAtLeastOneLogicTable(logicTables.toSet))

    def filterAllBindingTables(logicTables: List[String]): List[String] = {
        findBindingTableRule(logicTables)
            .map(tableRule => tableRule.getAllLogicTables)
            .map(bindingTables => bindingTables.filter(table => logicTables.contains(table)))
            .getOrElse(List())
    }

    def isAllBindingTables(logicTables: List[String]): Boolean =
        filterAllBindingTables(logicTables).containsSlice(logicTables)
}
