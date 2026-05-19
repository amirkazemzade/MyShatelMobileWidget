package me.amirkazemzade.netwidget.domain.models

data class LoginRequest(
    val username: String,
    val captchaResult: String
)