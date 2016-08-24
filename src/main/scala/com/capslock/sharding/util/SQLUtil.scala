package com.capslock.sharding.util

/**
 * Created by capslock1874.
 */
object SQLUtil {
    def getExactlyValue(value: String): String = value.replaceAll("[]`'\"", "")
}
