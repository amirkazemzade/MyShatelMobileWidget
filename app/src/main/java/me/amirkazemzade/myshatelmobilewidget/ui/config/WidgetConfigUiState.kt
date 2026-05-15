package me.amirkazemzade.myshatelmobilewidget.ui.config

import me.amirkazemzade.myshatelmobilewidget.domain.models.DataDisplayMode
import me.amirkazemzade.myshatelmobilewidget.domain.models.RemainedWidgetConfig

data class WidgetConfigUiState(
    val isLoading: Boolean = true,
    val remainedWidgetConfig: RemainedWidgetConfig = RemainedWidgetConfig(DataDisplayMode.PERCENTAGE),
    val isSaving: Boolean = false,
    val error: String? = null,
)
