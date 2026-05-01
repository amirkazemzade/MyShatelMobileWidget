package me.amirkazemzade.myshatelmobilewidget.ui.login.loginrequest

sealed interface LoginEvent {
    object LoginRequestSuccess: LoginEvent
    object LoginPasswordSuccess: LoginEvent
    data class Error(val message: String): LoginEvent
}