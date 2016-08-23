package com.capslock.sharding.parser.result.router

import com.capslock.sharding.parser.result.router.SQLBuilder.StringToken

/**
 * Created by capslock1874.
 */
class SQLBuilder extends Appendable {
    private var currentSegment = new StringBuilder()

    private var segments: List[Any] = List[Any](currentSegment)

    private var tokenMap = Map[String, StringToken]()

    def buildSQL(originToken: String, newToken: String): SQLBuilder = {
        for (oldToken <- tokenMap.get(originToken)) {
            oldToken.value = newToken
        }
        this
    }

    def containsToken(token: String): Boolean = tokenMap.contains(token)

    def toSQL: String = {
        segments.mkString
    }

    def appendToken(token: String): SQLBuilder = appendToken(token, isSetValue = true)

    def appendToken(token: String, isSetValue: Boolean): SQLBuilder = {
        val stringToken = tokenMap.getOrElse(token, {
            val tempStringToken = new StringToken()
            if (isSetValue) {
                tempStringToken.value = token
            }
            tokenMap += (token -> tempStringToken)
            tempStringToken
        })
        segments = segments ::: List(stringToken)
        currentSegment = new StringBuilder()
        segments = segments ::: List(currentSegment)
        this
    }


    override def append(csq: CharSequence): Appendable = {
        currentSegment.append(csq)
        this
    }

    override def append(csq: CharSequence, start: Int, end: Int): Appendable = {
        throw new UnsupportedOperationException
    }

    override def append(c: Char): Appendable = {
        currentSegment.append(c)
        this
    }
}

object SQLBuilder {

    class StringToken() {
        var value: String = ""

        def toToken: String = "[Token(".concat(value).concat(")]")
    }

}
