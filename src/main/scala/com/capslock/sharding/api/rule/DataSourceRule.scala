package com.capslock.sharding.api.rule

import javax.sql.DataSource

/**
 * Created by capslock1874.
 */
class DataSourceRule(val sourceMap: Map[String, DataSource], val defaultSourceName: String) {
    val dataSourceMap = sourceMap
    val defaultDataSourceName = defaultSourceName

    def getDataSource(name: String) = dataSourceMap.get(name)

    def getDefaultDataSource = dataSourceMap.get(defaultDataSourceName)

    def getDataSources = dataSourceMap.values.toList

    def getDataSourceNames = dataSourceMap.keySet
}

object DataSourceRule {
    def apply(sourceMap: Map[String, DataSource], defaultSourceName: String) {
        new DataSourceRule(sourceMap, defaultSourceName)
    }

    def apply(sourceMap: Map[String, DataSource]) {
        new DataSourceRule(sourceMap, "")
    }
}
