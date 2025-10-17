package me.amirkazemzade.myshatelmobilewidget.domain.models

data class Traffic(
    val amountInMb: Long,
) {
    fun toMB(): String = amountInMb.toString()
    fun toGB(): String = (amountInMb / 1024).toString()
}
