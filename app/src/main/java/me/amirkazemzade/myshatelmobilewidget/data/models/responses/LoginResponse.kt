package me.amirkazemzade.myshatelmobilewidget.data.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("display_captcha_password")
    val displayCaptchaPassword: Boolean,
    val message: String,
    val ok: Boolean,
    val url: String
)