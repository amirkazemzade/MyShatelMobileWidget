package me.amirkazemzade.myshatelmobilewidget.data.models.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemainedResponse(
    @SerialName("credit")
    val credit: String,
    @SerialName("internet_packages")
    val internetPackages: List<InternetPackage>,
    @SerialName("internet_percent")
    val internetPercent: List<String>,
    @SerialName("internet_used")
    val internetUsed: String,
    @SerialName("internet_used_unit")
    val internetUsedUnit: String,
    @SerialName("sms_packages")
    val smsPackages: List<String?>,
    @SerialName("sms_percent")
    val smsPercent: String,
    @SerialName("sms_used")
    val smsUsed: String,
    @SerialName("voice_packages")
    val voicePackages: List<String?>,
    @SerialName("voice_percent")
    val voicePercent: String,
    @SerialName("voice_used")
    val voiceUsed: String
)