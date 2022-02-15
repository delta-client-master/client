package com.deltaclient.common.ext

@Suppress("UNCHECKED_CAST")
fun <T : Any> Any.cast(): T {
    return this as T
}