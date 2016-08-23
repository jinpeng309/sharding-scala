package com.capslock.sharding.parser.visitor

import com.capslock.sharding.constants.DatabaseType
import com.capslock.sharding.parser.ParseContext
import com.capslock.sharding.parser.result.router.SQLBuilder


/**
 * Created by alvin.
 */
trait SQLVisitor {
    def getDatabaseType: DatabaseType.Type

    def getParseContext: ParseContext

    def getSQLBuilder: SQLBuilder

    def printToken(token: String)
}
