package com.capslock.sharding.constants

/**
 * Created by alvin.
 */
object DatabaseType {

    sealed trait Type

    case object H2 extends Type

    case object MySQL extends Type

    case object Oracle extends Type

    case object SQLServer extends Type

    case object DB2 extends Type

    case object PostgreSQL extends Type

    case object UNKNOWN extends Type

    def from(rawType: String): Type = {
        rawType match {
            case "H2" => H2
            case "MySQL" => MySQL
            case "Oracle" => Oracle
            case "SQLServer" => SQLServer
            case "DB2" => DB2
            case "PostgreSQL" => PostgreSQL
            case _ => UNKNOWN
        }
    }

}
