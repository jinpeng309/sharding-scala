package com.capslock.sharding.parser.result.router

/**
 * Created by alvin.
 */
object SQLStatementType {

    sealed trait Type

    case object INSERT extends Type

    case object SELECT extends Type

    case object UPDATE extends Type

    case object DELETE extends Type

    case object UNKNOWN extends Type

}
