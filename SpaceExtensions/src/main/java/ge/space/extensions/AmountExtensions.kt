package ge.space.extensions

import java.math.BigDecimal
import java.util.Locale

fun Long?.isNullOrZero(): Boolean {
    return (this == null || this == 0L)
}

fun Long.isNullOrMinusOne(): Boolean {
    return (this == null || this == -1L)
}

fun Int?.isNullOrZero(): Boolean {
    return (this == null || this == 0)
}

fun Int?.isNullOrMinusOne(): Boolean {
    return (this == null || this == -1)
}

fun Long.formatToDouble(): String? {
    return (this / AMOUNT_DIVIDER).toString()
}

fun Double.removeTrailingZeros(): String {
    val decimal = this.toBigDecimal()
    return if (decimal - decimal.toInt().toBigDecimal() > BigDecimal.ZERO)
        String.format(Locale.US, "%.2f", this)
    else this.toInt().toString()
}

const val AMOUNT_DIVIDER: Double = 100.0
