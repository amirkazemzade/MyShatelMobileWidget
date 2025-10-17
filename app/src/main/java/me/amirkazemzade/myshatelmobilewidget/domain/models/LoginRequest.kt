package me.amirkazemzade.myshatelmobilewidget.domain.models

data class LoginRequest(
    val username: String,
    val captchaResult: String
)