package me.amirkazemzade.myshatelmobilewidget.data.models.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    @SerialName("name_ar")
    val nameAr: String,
    @SerialName("name_en")
    val nameEn: String,
    @SerialName("name_fa")
    val nameFa: String
)