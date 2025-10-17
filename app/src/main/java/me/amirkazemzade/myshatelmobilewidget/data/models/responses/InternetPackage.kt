package me.amirkazemzade.myshatelmobilewidget.data.models.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InternetPackage(
    @SerialName("expire")
    val expire: String,
    @SerialName("expire_time")
    val expireTime: String,
    @SerialName("expire_timestamp")
    val expireTimestamp: String,
    @SerialName("half_price_value")
    val halfPriceValue: String,
    @SerialName("internal_social")
    val internalSocial: String,
    @SerialName("isRenewable")
    val isRenewable: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("packageInstanceID")
    val packageInstanceID: String,
    @SerialName("remained")
    val remained: String,
    @SerialName("remained_percentage")
    val remainedPercentage: String,
    @SerialName("renewabilityStatus")
    val renewabilityStatus: Boolean,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("status")
    val status: String
)