package com.space.formatter.translation

import android.content.Context
import androidx.annotation.StringRes
import java.lang.RuntimeException

class SPSlavicPluralTranslationFormatter(val context: Context) :
    SPPluralTranslationFormatter {

    override fun format(@StringRes initialResId: Int, num: Int): String {
        var mod = num % 100
        if (mod > 19)
            mod %= 10

        val suffix = when (mod) {
            1 -> ""
            in 2..4 -> "2"
            else -> "5"
        }

        val initialResourceName = context.resources.getResourceName(initialResId)
        val resourceName = initialResourceName + suffix
        val resId = context.resources.getIdentifier(resourceName, null, null)

        if (resId == 0)
           throw RuntimeException("Formatting error with key ${resourceName.substringAfter('/')}: No appropriate plural translations exist\"")

        return context.getString(resId, num.toString())
    }

}