package com.capslock.sharding.parser.visitor

import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlEvalVisitorImpl
import com.alibaba.druid.sql.visitor.SQLEvalVisitor

/**
 * Created by capslock1874.
 */
class MySQLEvalVisitor extends MySqlEvalVisitorImpl {
    override def visit(x: SQLVariantRefExpr): Boolean = {
        if (!"?".eq(x.getName)) {
            return false
        }
        val attributes = x.getAttributes
        val varIndex = x.getIndex
        if (varIndex == -1 || getParameters.size() <= varIndex) {
            return false
        }

        if (attributes.containsKey(SQLEvalVisitor.EVAL_VALUE)) {
            return false
        }
        var value = getParameters.get(varIndex)
        if (value == null) {
            value = SQLEvalVisitor.EVAL_VALUE_NULL
        }
        attributes.put(SQLEvalVisitor.EVAL_VALUE, value)
        attributes.put(MySQLEvalVisitor.EVAL_VAR_INDEX, new Integer(varIndex))
        false
    }
}

object MySQLEvalVisitor {
    val EVAL_VAR_INDEX: String = "EVAL_VAR_INDEX"
}
