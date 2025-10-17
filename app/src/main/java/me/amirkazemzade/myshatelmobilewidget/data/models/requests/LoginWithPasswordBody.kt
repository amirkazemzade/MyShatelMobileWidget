package me.amirkazemzade.myshatelmobilewidget.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginWithPasswordBody(
    val username: String,
    val password: String,
    val captcha: String,
)
