package com.capslock.sharding.parser.visitor

import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor
import com.capslock.sharding.constants.DatabaseType
import com.capslock.sharding.constants.DatabaseType.Type
import com.capslock.sharding.parser.ParseContext
import com.capslock.sharding.parser.result.router.SQLBuilder
import com.capslock.sharding.util.SQLUtil

/**
 * Created by capslock1874.
 */
class AbstractMySQLVisitor extends MySqlOutputVisitor(new SQLBuilder()) with SQLVisitor {
    setPrettyFormat(false)
    var parseContextIndex = 0
    var parseContext = new ParseContext(parseContextIndex)

    override def getDatabaseType: Type = DatabaseType.MySQL

    override def getSQLBuilder: SQLBuilder = appender.asInstanceOf[SQLBuilder]

    override def getParseContext: ParseContext = parseContext

    override def printToken(token: String): Unit = getSQLBuilder.appendToken(SQLUtil.getExactlyValue(token))
}
