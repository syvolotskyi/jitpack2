package com.space.formatter.format


interface SPFormatterFactory<T> {
    val defaultCurrency: T
    fun produceLabelAmountFormatter(grouping: Boolean = true): LongFormatter
    fun produceInputAmountFormatter(maxIntDigits: Int = getInputMaxIntDigits()): StringFormatter
    fun produceCardFormatter(): StringFormatter
    fun getInputMaxIntDigits(): Int
}