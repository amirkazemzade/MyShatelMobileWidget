package me.amirkazemzade.myshatelmobilewidget.ui.utils

import android.content.res.Resources
import android.text.TextPaint
import android.util.TypedValue

fun textFitsInContainer(
    text: String,
    textSizeSp: Float,
    containerWidthDp: Float,
    resources: Resources,
): Boolean {

    val textSizePx = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        textSizeSp,
        resources.displayMetrics
    )

    val containerWidthPx = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        containerWidthDp,
        resources.displayMetrics
    )


    val paint = TextPaint().apply {
        this.textSize = textSizePx
    }
    val textWidthPx = paint.measureText(text)

    return textWidthPx < containerWidthPx
}