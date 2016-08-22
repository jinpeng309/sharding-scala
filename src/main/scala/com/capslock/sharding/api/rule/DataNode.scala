package com.capslock.sharding.api.rule

/**
 * Created by capslock1874.
 */
class DataNode(val dataSourceName: String, val tableName: String) {

}

object DataNode {
    val DELIMITER = "."

    def isValidDataNode(rawDataNode: String): Boolean = {
        rawDataNode.contains(DELIMITER) && rawDataNode.split(DELIMITER).length == 2
    }

    def apply(rawDataNode: String): DataNode = {
        val dataSourceName = rawDataNode.split(DataNode.DELIMITER)(0)
        val tableName = rawDataNode.split(DataNode.DELIMITER)(1)
        new DataNode(dataSourceName, tableName)
    }

    def apply(dataSourceName: String, tableName: String): DataNode = {
        new DataNode(dataSourceName, tableName)
    }
}
