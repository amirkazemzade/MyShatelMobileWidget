package me.amirkazemzade.myshatelmobilewidget.ui.config

sealed class WidgetConfigEvent {
    data class Error(val message: String) : WidgetConfigEvent()
    object Success : WidgetConfigEvent()
}