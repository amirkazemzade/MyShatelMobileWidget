package me.amirkazemzade.myshatelmobilewidget.data.models.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MsisdnsInfoResponseItem(
    @SerialName("icc")
    val icc: String?,
    @SerialName("idSubscriber")
    val idSubscriber: String?,
    @SerialName("msisdn")
    val msisdn: String,
    @SerialName("subscriptionID")
    val subscriptionID: String?,
    @SerialName("terminationInfo")
    val terminationInfo: TerminationInfo?
)