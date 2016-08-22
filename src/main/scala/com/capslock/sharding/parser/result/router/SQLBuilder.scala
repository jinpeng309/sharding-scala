package com.capslock.sharding.parser.result.router

/**
 * Created by capslock1874.
 */
class SQLBuilder extends Appendable {
    private var segments = List[Any]()


    override def append(csq: CharSequence): Appendable = ???

    override def append(csq: CharSequence, start: Int, end: Int): Appendable = ???

    override def append(c: Char): Appendable = ???
}

object SQLBuilder {

    case class StringToken(value: String) {
        def toToken: String = "[Token(".concat(value).concat(")]")
    }

}
