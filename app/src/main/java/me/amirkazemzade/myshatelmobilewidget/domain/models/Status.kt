package me.amirkazemzade.myshatelmobilewidget.domain.models

sealed interface Status<out T> {
    object Idle : Status<Nothing>
}