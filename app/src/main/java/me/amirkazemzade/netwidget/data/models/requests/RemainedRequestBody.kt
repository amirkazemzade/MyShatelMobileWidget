package me.amirkazemzade.netwidget.data.models.requests


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemainedRequestBody(
    @SerialName("msisdn")
    val msisdn: String
)