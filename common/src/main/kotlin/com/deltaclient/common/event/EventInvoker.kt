package com.deltaclient.common.event

import java.lang.reflect.Method
import java.util.function.Consumer

class EventInvoker(private val method: Method, private val parent: Any) : Consumer<Any> {
    override fun accept(t: Any) {
        // FIXME: Make this used cached method handles for performance gain

        method.trySetAccessible()
        method.invoke(parent, t)
    }
}