package me.amirkazemzade.netwidget.data.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class CaptchaResponse(
    val captcha: String
)