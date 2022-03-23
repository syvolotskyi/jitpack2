package com.space.models.model.amount

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SPAmount(
    var amount: Long,
    val currencyCode: SPCurrencyType
): Parcelable