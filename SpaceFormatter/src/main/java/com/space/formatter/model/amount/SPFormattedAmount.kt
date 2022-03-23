package com.space.formatter.model.amount

import com.space.models.model.amount.SPCurrencyType

data class SPFormattedAmount(val amount: String, val currency : SPCurrencyType){
    val formattedAmount = "$amount${currency.symbol}"
    val formattedAmountWithSpace = "$amount ${currency.symbol}"
}