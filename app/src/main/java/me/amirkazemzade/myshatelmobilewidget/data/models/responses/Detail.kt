package me.amirkazemzade.myshatelmobilewidget.data.models.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Detail(
    @SerialName("adsl_internet_traffic")
    val adslInternetTraffic: Long,
    @SerialName("internet_traffic")
    val internetTraffic: Long,
    @SerialName("internet_traffic_range")
    val internetTrafficRange: String,
    @SerialName("sms_offnet")
    val smsOffnet: Int,
    @SerialName("sms_onnet")
    val smsOnnet: Int,
    @SerialName("voice_minutes_offnet")
    val voiceMinutesOffnet: Int,
    @SerialName("voice_minutes_onnet")
    val voiceMinutesOnnet: Int
)