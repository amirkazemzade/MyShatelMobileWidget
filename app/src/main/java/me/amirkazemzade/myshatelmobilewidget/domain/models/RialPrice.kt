package me.amirkazemzade.myshatelmobilewidget.domain.models

import android.annotation.SuppressLint

@SuppressLint("DefaultLocale")
data class RialPrice(val value: Long) {
    fun toTomanValue(): Long = value / 10

    fun toFormattedTomanString(): String = "${String.format("%,d", toTomanValue())} Toman"
    fun toFormattedRialString(): String = "${String.format("%,d", value)} Rial"
}
