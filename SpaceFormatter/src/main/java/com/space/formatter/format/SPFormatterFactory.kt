package com.space.formatter.format


interface SPFormatterFactory {
    fun produceLabelAmountFormatter(grouping: Boolean = true): LongFormatter
    fun produceInputAmountFormatter(maxIntDigits: Int = getInputMaxIntDigits()): StringFormatter
    fun produceCardFormatter(): StringFormatter
    fun getInputMaxIntDigits(): Int
}