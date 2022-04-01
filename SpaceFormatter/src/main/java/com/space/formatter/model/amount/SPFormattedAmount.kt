package com.space.formatter.model.amount


data class SPFormattedAmount(val amount: String, val currency : String){
    val formattedAmount = "$amount${currency}"
    val formattedAmountWithSpace = "$amount ${currency}"
}