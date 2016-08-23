package com.capslock.sharding.parser.result.merger

/**
 * Created by capslock1874.
 */
trait IndexColumn {
    def setColumnIndex(index: Int)

    def getColumnIndex: Int

    def getColumnLabel: Option[String]

    def getColumnName: Option[String]
}
