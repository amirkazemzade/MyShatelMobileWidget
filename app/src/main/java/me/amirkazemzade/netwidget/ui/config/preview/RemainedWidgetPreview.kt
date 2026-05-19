package me.amirkazemzade.netwidget.ui.config.preview

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import me.amirkazemzade.netwidget.domain.models.Remained
import me.amirkazemzade.netwidget.domain.models.Traffic
import me.amirkazemzade.netwidget.ui.theme.MyShatelMobileAppTheme
import me.amirkazemzade.netwidget.ui.utils.textFitsInContainer
import me.amirkazemzade.netwidget.ui.widgets.components.dynamicPercentagePadding
import me.amirkazemzade.netwidget.domain.models.DataDisplayMode
import kotlin.math.roundToInt
import kotlin.math.roundToLong

@Composable
fun RemainedWidgetPreview(
    dataDisplayMode: DataDisplayMode,
    modifier: Modifier = Modifier,
) {
    val remained = Remained(
        traffic = Traffic((6.7f * 1024).roundToLong()),
        percentage = 0.67f,
    )

    BoxWithConstraints(
        modifier = modifier,
    ) {
        val widgetSize = DpSize(maxWidth, 80.dp)
        RemainedWidgetPreviewContent(
            widgetSize = widgetSize,
            remained = remained,
            dataDisplayMode = dataDisplayMode,
        )
    }
}

@Composable
private fun RemainedWidgetPreviewContent(
    widgetSize: DpSize,
    remained: Remained,
    dataDisplayMode: DataDisplayMode,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(widgetSize)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(12.dp),
        contentAlignment = Alignment.Center,
    ) {
        PillContent(
            widgetSize = widgetSize,
            remained = remained,
            dataDisplayMode = dataDisplayMode,
        )
    }
}

@SuppressLint("LocalContextResourcesRead")
@Composable
private fun PillContent(
    widgetSize: DpSize,
    remained: Remained,
    dataDisplayMode: DataDisplayMode,
) {
    val breakPoint = 0.33f
    val isLow = remained.percentage < breakPoint

    val pillWidth = (widgetSize.width - 24.dp) * remained.percentage

    val textPadding = dynamicPercentagePadding(
        remained.percentage,
        maxPadding = 16.dp,
        minPadding = 4.dp
    )

    val dataInfoText = when (dataDisplayMode) {
        DataDisplayMode.PERCENTAGE ->
            "${(remained.percentage * 100).roundToInt()}%"

        DataDisplayMode.TRAFFIC -> remained.traffic.toReadableText()
    }

    val fontSize = when (dataDisplayMode) {
        DataDisplayMode.PERCENTAGE -> 36.sp
        DataDisplayMode.TRAFFIC -> 24.sp
    }

    val textFits = textFitsInContainer(
        text = dataInfoText,
        textSizeSp = fontSize.value + 1,
        containerWidthDp = pillWidth.value,
        resources = LocalContext.current.resources
    )

    val pillColor =
        if (isLow) MaterialTheme.colorScheme.error
        else MaterialTheme.colorScheme.primary

    val textStyle = TextStyle(
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    )

    val showTextInside = !isLow && textFits

    PillUi(
        pillWidth = pillWidth,
        pillColor = pillColor,
        showTextInside = showTextInside,
        textPadding = textPadding,
        dataInfoText = dataInfoText,
        textStyle = textStyle
    )
}

@Composable
private fun PillUi(
    pillWidth: Dp,
    pillColor: Color,
    showTextInside: Boolean,
    textPadding: Dp,
    dataInfoText: String,
    textStyle: TextStyle,
) {
    val outerColor = MaterialTheme.colorScheme.surfaceVariant
    val textColor = MaterialTheme.colorScheme.onPrimary
    val shape = RoundedCornerShape(16.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = outerColor,
                shape = shape,
            )
    ) {

        Box(
            modifier = Modifier
                .width(max(pillWidth, 2.dp))
                .fillMaxHeight()
                .background(pillColor, shape = shape),
            contentAlignment = Alignment.CenterEnd
        ) {

            if (showTextInside) {
                Text(
                    modifier = Modifier.padding(end = textPadding),
                    text = dataInfoText,
                    maxLines = 1,
                    style = textStyle.copy(
                        color = textColor,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

        if (!showTextInside) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = pillWidth + textPadding),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = dataInfoText,
                    style = textStyle.copy(color = pillColor)
                )
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun RemainedWidgetComposePreviewPercentage() {
    RemainedWidgetComposePreview(
        DataDisplayMode.PERCENTAGE
    )
}

@PreviewLightDark
@Composable
private fun RemainedWidgetComposePreviewTraffic() {
    RemainedWidgetComposePreview(
        DataDisplayMode.TRAFFIC
    )
}

@Composable
private fun RemainedWidgetComposePreview(
    dataDisplayMode: DataDisplayMode,
) {
    MyShatelMobileAppTheme {
        Surface {
            RemainedWidgetPreview(
                dataDisplayMode = dataDisplayMode,
            )
        }
    }
}
