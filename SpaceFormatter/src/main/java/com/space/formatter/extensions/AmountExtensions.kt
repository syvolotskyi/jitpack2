package com.space.formatter.extensions

import com.space.formatter.format.SPDefaultFormatterFactory
import com.space.formatter.model.amount.SPFormattedAmount
import com.space.models.model.amount.SPAmount
import com.space.models.model.amount.SPCurrencyType
import java.math.BigDecimal
import java.util.*

fun Double.formatToAmount(currency: SPCurrencyType = SPDefaultFormatterFactory.defaultCurrency): SPAmount {
    val amount = SPDefaultFormatterFactory.produceLabelAmountFormatter().undoFormat(this.toString())
    return SPAmount(amount, currency)
}

fun String.formatToAmount(currency: SPCurrencyType = SPDefaultFormatterFactory.defaultCurrency): SPAmount {
    val amount = SPDefaultFormatterFactory.produceLabelAmountFormatter().undoFormat(this)
    return SPAmount(amount, currency)
}

fun Long.formatToAmount(currency: SPCurrencyType = SPDefaultFormatterFactory.defaultCurrency): SPAmount {
    return SPAmount(this, currency)
}

fun BigDecimal.formatToAmount(currency: SPCurrencyType = SPDefaultFormatterFactory.defaultCurrency): SPAmount {
    val amount = SPDefaultFormatterFactory.produceLabelAmountFormatter().undoFormat(this.toString())
    return SPAmount(amount, currency)
}
fun SPFormattedAmount.formatToAmount(): SPAmount {
    val amount = SPDefaultFormatterFactory.produceLabelAmountFormatter().undoFormat(this.amount)
    return SPAmount(amount, this.currency)
}

fun Double.formatToCoins(): Long {
    return SPDefaultFormatterFactory.produceLabelAmountFormatter().undoFormat(this.toString())
}

fun String.formatToCoins(): Long {
    return SPDefaultFormatterFactory.produceLabelAmountFormatter().undoFormat(this)
}

fun SPFormattedAmount.formatToCoins(): Long {
    return SPDefaultFormatterFactory.produceLabelAmountFormatter().undoFormat(this.amount)
}

fun SPAmount.formatToCurrency(setGroupingUsed: Boolean = true): SPFormattedAmount {
    val amount =
        SPDefaultFormatterFactory.produceLabelAmountFormatter(setGroupingUsed).format(this.amount)
    return SPFormattedAmount(amount = amount, currency = this.currencyCode)
}

fun SPAmount.isZero(): Boolean {
    return this.amount == 0L
}

fun SPAmount.isNotZero(): Boolean {
    return this.amount != 0L
}

fun Long.formatToCurrency(
    setGroupingUsed: Boolean = true,
    currency: SPCurrencyType = SPDefaultFormatterFactory.defaultCurrency
): SPFormattedAmount {
    val amount =
        SPDefaultFormatterFactory.produceLabelAmountFormatter(setGroupingUsed).format(this)
    return SPFormattedAmount(amount = amount, currency = currency)
}

fun String.formatToCurrency(currency: SPCurrencyType = SPDefaultFormatterFactory.defaultCurrency): SPFormattedAmount {
    return SPFormattedAmount(amount = this, currency = currency)
}

fun String.convertToDouble() = this.replace(",", "").replace(" ", "").toDouble()

fun Double.removeTrailingZeros(): String {
    val decimal = this.toBigDecimal()
    return if (decimal - decimal.toInt().toBigDecimal() > BigDecimal.ZERO)
        String.format(Locale.US, "%.2f", this)
    else this.toInt().toString()
}

fun Long.formatToDouble(): String? {
    val amountDivider = 100.0
    return (this / amountDivider).toString()
}

fun SPAmount?.plus(amount: Long) = this?.amount?.plus(amount)

fun SPAmount?.plus(amount: SPAmount?) = this?.amount?.plus(amount?.amount ?: 0)

fun getEmptyAmount(currency: SPCurrencyType = SPDefaultFormatterFactory.defaultCurrency) =
    SPAmount(0, currency)

fun getEmptyFormattedAmount(currency: SPCurrencyType = SPDefaultFormatterFactory.defaultCurrency) =
    SPFormattedAmount("0", currency)