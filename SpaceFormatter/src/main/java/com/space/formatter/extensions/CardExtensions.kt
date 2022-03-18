package com.space.formatter.extensions

import com.space.formatter.format.SPDefaultFormatterFactory

fun String.formatToCardNumber(): String {
    return SPDefaultFormatterFactory.produceCardFormatter().format(this)
}
