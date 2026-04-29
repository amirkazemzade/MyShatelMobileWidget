package me.amirkazemzade.myshatelmobilewidget.widgets.components

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.lerp

fun dynamicPercentagePadding(percentage: Float, maxPadding: Dp, minPadding: Dp): Dp {
    val clamped = percentage.coerceIn(0f, 1f)
    // high p → factor ≈ 1 → near maxPadding
    // low p  → factor ≈ 0 → near minPadding
    val factor = clamped // you can tweak this curve later
    return lerp(minPadding, maxPadding, factor)
}