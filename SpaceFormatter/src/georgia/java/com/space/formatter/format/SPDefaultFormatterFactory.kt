package com.space.formatter.format


object SPDefaultFormatterFactory :
    SPFormatterFactory{

    override fun produceLabelAmountFormatter(grouping: Boolean): LongFormatter =
        SPLabelAmountFormatter(SPFractioningStrategies.DontTrimZerosUnlessWhole)
            .apply {
            fractionPrecision = FRACTION_PRECISION
            separator = SEPARATOR
            group = grouping
        }

    override fun produceInputAmountFormatter(maxIntDigits: Int): StringFormatter =
        SPInputAmountFormatter().apply {
            maxIntegerDigits = maxIntDigits
            maxFractionDigits = FRACTION_PRECISION
            separator = SEPARATOR
        }

    override fun produceCardFormatter() =
        SPCardNumberFormatter()

    override fun getInputMaxIntDigits() = MAX_INTEGER_DIGITS

    private const val FRACTION_PRECISION = 2
    private const val MAX_INTEGER_DIGITS = 5
    private const val SEPARATOR = ','
}