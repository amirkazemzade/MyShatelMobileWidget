package me.amirkazemzade.netwidget.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginBody(
    val username: String,
    val captcha: String,
)
