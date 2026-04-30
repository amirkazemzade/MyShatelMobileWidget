package me.amirkazemzade.myshatelmobilewidget.widgets.remained

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import me.amirkazemzade.myshatelmobilewidget.MainActivity
import me.amirkazemzade.myshatelmobilewidget.data.datasource.RemainedLocalDataSource
import me.amirkazemzade.myshatelmobilewidget.domain.models.Remained
import me.amirkazemzade.myshatelmobilewidget.domain.models.Traffic
import me.amirkazemzade.myshatelmobilewidget.widgets.components.dynamicPercentagePadding
import kotlin.math.roundToInt

class RemainedGlanceWidget1x4 : GlanceAppWidget() {

    override val stateDefinition = PreferencesGlanceStateDefinition

    override val sizeMode: SizeMode
        get() = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val dataSource = RemainedLocalDataSource(context)

        provideContent {
            val remained by dataSource.remainedData.collectAsState(initial = null)

            val widgetSize = LocalSize.current
            GlanceTheme {
                Content(
                    widgetSize = widgetSize,
                    remained = remained,
                )
            }
        }
    }

    @Composable
    fun Content(
        widgetSize: DpSize,
        remained: Remained?,
    ) {
        val context = LocalContext.current

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .cornerRadius(24.dp)
                .background(GlanceTheme.colors.widgetBackground)
                .padding(12.dp)
                .clickable(
                    onClick = actionStartActivity(
                        Intent(context, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        }
                    ),
                ),
            contentAlignment = Alignment.Center,
        ) {
            when (remained) {
                null -> NotFoundContent()
                else -> PercentageContent(
                    widgetSize = widgetSize,
                    remained = remained,
                )
            }
        }
    }

    @Composable
    private fun NotFoundContent() {
        Text(
            text = "No Content Available",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = GlanceTheme.colors.primary,
            )
        )

    }

    @Composable
    private fun PercentageContent(
        widgetSize: DpSize,
        remained: Remained,
    ) {
        val pillWidth = (widgetSize.width - 24.dp) * remained.percentage
        val breakPoint = 0.33f
        val isLow = remained.percentage < breakPoint

        val percentageTextPadding =
            dynamicPercentagePadding(remained.percentage, maxPadding = 16.dp, minPadding = 4.dp)
        val percentageText = (remained.percentage * 100).roundToInt()

        "%.2f GB".format(remained.traffic.toGB())

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(GlanceTheme.colors.surfaceVariant)
                .cornerRadius(16.dp)
        ) {
            val pillColor =
                if (!isLow) GlanceTheme.colors.primary
                else GlanceTheme.colors.error
            Box(
                modifier = GlanceModifier
                    .width(max(pillWidth, 2.dp))
                    .fillMaxHeight()
                    .background(pillColor)
                    .cornerRadius(16.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                if (!isLow) {
                    Text(
                        modifier = GlanceModifier.padding(end = percentageTextPadding),
                        text = "$percentageText%",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = GlanceTheme.colors.onPrimary,
                        )
                    )
                }
            }

            if (isLow) {
                Box(
                    modifier = GlanceModifier
                        .fillMaxHeight()
                        .padding(start = pillWidth + percentageTextPadding),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "$percentageText%",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            color = GlanceTheme.colors.error,
                        )
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreviewNoContent() {
    GlanceTheme {

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = null,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview100Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 1f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview80Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 0.8f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview60Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 0.6f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview50Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 0.5f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview40Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 0.4f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview33Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 0.33f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview32Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 0.32f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview20Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 0.2f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun RemainedGlanceWidgetPreview0Percent() {
    GlanceTheme {
        val remained = Remained(
            traffic = Traffic(7000),
            percentage = 0.0f,
        )

        val widgetSize = DpSize(280.dp, 80.dp)

        Box(
            modifier = GlanceModifier.size(
                width = widgetSize.width,
                height = widgetSize.height,
            )
        ) {
            RemainedGlanceWidget1x4().Content(
                widgetSize = widgetSize,
                remained = remained,
            )
        }
    }
}

