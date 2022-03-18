package com.space.formatter.format

import com.space.models.model.amount.SPCurrencyType

interface SPFormatterFactory {
    val defaultCurrency: SPCurrencyType
    fun produceLabelAmountFormatter(grouping: Boolean = true): LongFormatter
    fun produceInputAmountFormatter(maxIntDigits: Int = getInputMaxIntDigits()): StringFormatter
    fun produceCardFormatter(): StringFormatter
    fun getInputMaxIntDigits(): Int
}