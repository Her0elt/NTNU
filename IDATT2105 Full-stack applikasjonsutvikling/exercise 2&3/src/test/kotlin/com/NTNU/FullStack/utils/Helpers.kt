package com.NTNU.FullStack.utils

import kotlin.streams.asSequence

fun getRandomString(size: Long): String {
    val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return java.util.Random().ints(size, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString("")
}