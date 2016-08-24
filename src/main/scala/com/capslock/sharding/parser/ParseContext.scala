package com.capslock.sharding.parser

import com.alibaba.druid.sql.ast.expr.{SQLIdentifierExpr, SQLMethodInvokeExpr, SQLPropertyExpr}
import com.alibaba.druid.sql.ast.{SQLExpr, SQLObject}
import com.alibaba.druid.sql.visitor.SQLEvalVisitorUtils
import com.capslock.sharding.constants.DatabaseType
import com.capslock.sharding.parser.ParseContext.ValuePair
import com.capslock.sharding.parser.result.SQLParsedResult
import com.capslock.sharding.parser.result.router.Condition.{BinaryOperator, Column}
import com.capslock.sharding.parser.result.router.{ConditionContext, Table}
import com.capslock.sharding.parser.visitor.MySQLEvalVisitor
import com.capslock.sharding.util.SQLUtil.getExactlyValue

import scala.collection.JavaConversions

/**
 * Created by capslock1874.
 */
class ParseContext(var parseContextIndex: Int) {
    val autoGenTokenKey = ParseContext.AUTO_GEN_TOKE_KEY_TEMPLATE.format(parseContextIndex)
    val parsedResult = new SQLParsedResult()
    val shardingColumns = List[Column]()
    val currentConditionContext = new ConditionContext()
    var hasOrCondition = false
    var currentTable: Option[Table] = Option.empty
    var selectItemsCount = 0
    var selectItems = Set[String]()
    var parentParseContext: Option[ParseContext] = Option.empty
    var subParseContext = List[ParseContext]()
    var itemIndex = 0

    def setCurrentTable(tableName: String, alias: Option[String]): Unit = {
        val table = Table(getExactlyValue(tableName), alias.map(name => getExactlyValue(name)))
        parsedResult.routeContext.addTable(table)
    }

    def addCondition(expr: SQLExpr, operator: BinaryOperator, valueExprList: List[SQLExpr],
        databaseType: DatabaseType.Type, parameters: List[Any]): Unit = {
        for (column <- getColumn(expr) if shardingColumns.contains(column.columnName)) {

        }
    }

    def evalExpression(databaseType: DatabaseType.Type, sqlObject: SQLObject, parameters: List[AnyRef]): Option[ValuePair] = {
        if (sqlObject.isInstanceOf[SQLMethodInvokeExpr]) {
            return Option.empty
        }

        val visitor = new MySQLEvalVisitor()
        visitor.setParameters(JavaConversions.seqAsJavaList(parameters))
        sqlObject.accept(visitor)
        val value = SQLEvalVisitorUtils.getValue(sqlObject)
        val finalValue = if (value.isInstanceOf[Comparable[AnyRef]]) value else ""
        var index = sqlObject.getAttribute(MySQLEvalVisitor.EVAL_VAR_INDEX)
        if (index == null) {
            index = new Integer(-1)
        }
        Some(ValuePair(finalValue.asInstanceOf[Comparable[AnyRef]], index.asInstanceOf[Int]))
    }

    def getColumn(expr: SQLExpr): Option[Column] = {
        expr match {
            case _: SQLPropertyExpr => getColumnWithQualifiedName(expr.asInstanceOf[SQLPropertyExpr])
            case _: SQLIdentifierExpr => getColumnWithoutAlias(expr.asInstanceOf[SQLIdentifierExpr])
            case _ => Option.empty
        }
    }

    def getColumnWithoutAlias(expr: SQLIdentifierExpr): Option[Column] = {
        for (table <- currentTable)
            yield Column(getExactlyValue(expr.getName), getExactlyValue(table.name))
    }

    def getColumnWithQualifiedName(expr: SQLPropertyExpr): Option[Column] = {
        findTable(expr.getOwner.asInstanceOf[SQLIdentifierExpr].getName).map(table =>
            Column(getExactlyValue(expr.getName), table.name))
    }

    def findTable(nameOrAlias: String): Option[Table] = {
        findTableFromName(nameOrAlias).orElse(findTableFromAlias(nameOrAlias))
    }

    def findTableFromName(name: String): Option[Table] = {
        parsedResult.routeContext.tables.find(table => table.name.equalsIgnoreCase(name))
    }

    def findTableFromAlias(alias: String): Option[Table] = {
        parsedResult.routeContext.tables.find(table => alias.equalsIgnoreCase(table.alias.getOrElse("")))
    }

}

object ParseContext {
    val AUTO_GEN_TOKE_KEY_TEMPLATE = "sharding_auto_gen_%d"
    val SHARDING_GEN_ALIAS = "sharding_gen_%s"

    case class ValuePair(value: Comparable[AnyRef], paramIndex: Int)

}
