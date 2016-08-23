package com.capslock.sharding.parser.visitor

import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor
import com.capslock.sharding.parser.ParseContext
import com.capslock.sharding.parser.result.router.SQLBuilder

/**
 * Created by capslock1874.
 */
class AbstractMySQLVisitor extends MySqlOutputVisitor(new SQLBuilder()) with SQLVisitor {
//    private var parseContext: ParseContext = null
//    private var parseContextIndex: Int = 0

}
