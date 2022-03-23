package com.space.formatter.translation

import androidx.annotation.StringRes

interface SPPluralTranslationFormatter {
    fun format(@StringRes initialResId: Int, num: Int): String
}