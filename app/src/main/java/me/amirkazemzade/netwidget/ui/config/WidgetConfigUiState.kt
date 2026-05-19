package me.amirkazemzade.netwidget.ui.config

import me.amirkazemzade.netwidget.domain.models.DataDisplayMode
import me.amirkazemzade.netwidget.domain.models.RemainedWidgetConfig

data class WidgetConfigUiState(
    val isLoading: Boolean = true,
    val remainedWidgetConfig: RemainedWidgetConfig = RemainedWidgetConfig(DataDisplayMode.PERCENTAGE),
    val isSaving: Boolean = false,
    val error: String? = null,
)
