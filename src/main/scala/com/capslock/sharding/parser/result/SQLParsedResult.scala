package com.capslock.sharding.parser.result

import com.capslock.sharding.parser.result.merger.MergeContext
import com.capslock.sharding.parser.result.router.{ConditionContext, RouteContext}

/**
 * Created by capslock1874.
 */
class SQLParsedResult {
    val routeContext = new RouteContext()
    val conditionContexts = List[ConditionContext]()
    val mergeContext = List[MergeContext]()
}
