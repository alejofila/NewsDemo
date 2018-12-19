package com.elements.interviewtest.browseNews

import org.mockito.Mockito


fun <T> anything(): T {
    Mockito.any<T>()
    return uninitialized()
}

fun <T> uninitialized(): T = null as T