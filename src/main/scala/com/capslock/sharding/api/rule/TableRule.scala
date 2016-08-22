package com.capslock.sharding.api.rule

/**
 * Created by capslock1874.
 */
class TableRule(val logicTable: String, val dynamic: Boolean, val actualTables: List[DataNode],
    val databaseShardingStrategy: Option[DatabaseShardingStrategy],
    val tableShardingStrategy: Option[TableShardingStrategy]) {

    def getStaticDataNodes(targetDataSources: List[String], targetTables: List[String]): List[DataNode] = {
        actualTables.filter(dataNode =>
            targetDataSources.contains(dataNode.dataSourceName) && targetTables.contains(dataNode.tableName))
    }

    def getActualDataNodes(targetDataSources: List[String], targetTables: List[String]): List[DataNode] = {
        if (dynamic) List() else getStaticDataNodes(targetDataSources, targetTables)
    }

    def getActualDatasourceNames = actualTables.map(dataNode => dataNode.dataSourceName)

    def getActualTableNames = actualTables.map(dataNode => dataNode.tableName)

    def findActualTableIndex(dataSourceName: String, actualTableName: String): Int = {
        actualTables.indexOf(DataNode(dataSourceName, actualTableName))
    }
}

object TableRule {

    def generateDataNode(logicTable: String, dataSourceRule: DataSourceRule): List[DataNode] = {
        dataSourceRule.getDataSourceNames.map(sourceName => DataNode(sourceName, logicTable)).toList
    }

    def generateDataNode(logicTable: String, dataSourceNames: List[String]): List[DataNode] = {
        dataSourceNames.map(sourceName => DataNode(sourceName, logicTable))
    }

    def apply(logicTable: String, dynamic: Boolean, dataSourceRule: DataSourceRule): TableRule = {
        apply(logicTable, dynamic, dataSourceRule, null, null)
    }


    def apply(logicTable: String, dynamic: Boolean, dataSourceRule: DataSourceRule, databaseShardingStrategy: DatabaseShardingStrategy,
        tableShardingStrategy: TableShardingStrategy): TableRule = {
        val actualTables = generateDataNode(logicTable, dataSourceRule).sortBy(dataNode =>
            dataNode.dataSourceName.concat(dataNode.tableName))
        new TableRule(logicTable, dynamic, actualTables, Option(databaseShardingStrategy), Option(tableShardingStrategy))
    }


    def apply(logicTable: String, dynamic: Boolean, dataSourceNames: List[String]): TableRule = {
        apply(logicTable, dynamic, dataSourceNames, null, null)
    }


    def apply(logicTable: String, dynamic: Boolean, dataSourceNames: List[String], databaseShardingStrategy: DatabaseShardingStrategy,
        tableShardingStrategy: TableShardingStrategy): TableRule = {
        val actualTables = generateDataNode(logicTable, dataSourceNames)
        new TableRule(logicTable, dynamic, actualTables, Option(databaseShardingStrategy), Option(tableShardingStrategy))
    }

    def apply(logicTable: String, actualTables: List[String], dynamic: Boolean): TableRule = {
        apply(logicTable, actualTables, dynamic, null, null)
    }

    def apply(logicTable: String, actualTableNames: List[String], dynamic: Boolean, databaseShardingStrategy: DatabaseShardingStrategy,
        tableShardingStrategy: TableShardingStrategy): TableRule = {
        val actualTables = generateDataNode(actualTableNames)
        new TableRule(logicTable, dynamic, actualTables, Option(databaseShardingStrategy), Option(tableShardingStrategy))
    }

    def generateDataNode(actualTables: List[String]): List[DataNode] = {
        actualTables.filter(tableName => DataNode.isValidDataNode(tableName)).map(tableName => DataNode(tableName))
    }


}