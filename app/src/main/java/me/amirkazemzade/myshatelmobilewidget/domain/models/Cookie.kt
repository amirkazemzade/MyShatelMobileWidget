package me.amirkazemzade.myshatelmobilewidget.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Cookie(
    val session: String,
    val path: String,
    val expires: String,
)
