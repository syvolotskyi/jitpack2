package com.space.formatter.format

class SPCardNumberFormatter : StringFormatter {

    private val separatorRegex = (".{$CARD_NUMBER_PARTITION_LENGTH}(?!$)").toRegex()

    override fun format(input: String) = undoFormat(input).take(CARD_NUMBER_LENGTH).replace(separatorRegex, "$0 ")

    override fun undoFormat(formattedText: String) = formattedText.replace(" ", "")

    companion object {
        private const val CARD_NUMBER_LENGTH = 16
        private const val CARD_NUMBER_PARTITION_LENGTH = 4
    }

}