package com.space.formatter.extensions

import java.math.BigDecimal
import java.math.RoundingMode

fun Double.digitValidation(): String =  BigDecimal(this).setScale(4, RoundingMode.HALF_UP).toString()