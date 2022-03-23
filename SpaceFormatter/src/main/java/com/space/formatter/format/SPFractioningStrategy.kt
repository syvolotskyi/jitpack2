package com.space.formatter.format

import java.text.DecimalFormat

interface SPFractioningStrategy {
    fun apply(decimalFormat: DecimalFormat, fractionDigits: Int, number: Double)
}