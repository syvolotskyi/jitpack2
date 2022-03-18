package com.space.formatter.format

interface SPFormatter<I> {
    fun format(input: I): String
    fun undoFormat(formattedText: String): I
}

typealias LongFormatter = SPFormatter<Long>
typealias StringFormatter = SPFormatter<String>