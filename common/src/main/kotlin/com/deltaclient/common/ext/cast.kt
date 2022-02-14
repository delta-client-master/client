package com.deltaclient.common.ext

fun <T : Any> Any.cast(): T {
    return this as T
}