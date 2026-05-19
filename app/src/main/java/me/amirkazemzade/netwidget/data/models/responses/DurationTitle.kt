package me.amirkazemzade.netwidget.data.models.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DurationTitle(
    @SerialName("duration_title_ar")
    val durationTitleAr: String,
    @SerialName("duration_title_en")
    val durationTitleEn: String,
    @SerialName("duration_title_fa")
    val durationTitleFa: String
)