package com.space.formatter.translation

import android.content.Context
import androidx.annotation.StringRes

class SPNoPluralTranslationFormatter(val context: Context) :
    SPPluralTranslationFormatter {
    override fun format(@StringRes initialResId: Int, num: Int): String {
        return context.getString(initialResId, num)
    }

}