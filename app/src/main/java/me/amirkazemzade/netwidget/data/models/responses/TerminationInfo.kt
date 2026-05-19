package me.amirkazemzade.netwidget.data.models.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TerminationInfo(
    @SerialName("status_code")
    val statusCode: Int?
)