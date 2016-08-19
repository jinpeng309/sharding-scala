package com.capslock.sharding.api.rule

/**
 * Created by alvin.
 */
class DataNode(val rawDataNode: String) {
    val dataSourceName = rawDataNode.split(DataNode.DELIMITER)(0)
    val tableName = rawDataNode.split(DataNode.DELIMITER)(1)
}

object DataNode {
    val DELIMITER = "."

    def isValidDataNode(rawDataNode: String): Boolean = {
        rawDataNode.contains(DELIMITER) && rawDataNode.split(DELIMITER).length == 2
    }
}
