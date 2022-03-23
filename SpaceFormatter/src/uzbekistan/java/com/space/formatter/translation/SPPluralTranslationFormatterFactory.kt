package com.space.formatter.translation

import android.content.Context

object SPPluralTranslationFormatterFactory {
    fun provide(context: Context): SPPluralTranslationFormatter = SPSlavicPluralTranslationFormatter(context)
}