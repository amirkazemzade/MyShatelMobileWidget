package me.amirkazemzade.myshatelmobilewidget.domain.models

import kotlin.time.Duration

data class InternetPackage(
    val traffic: Traffic,
    val duration: Duration,
    val price: RialPrice,
)
