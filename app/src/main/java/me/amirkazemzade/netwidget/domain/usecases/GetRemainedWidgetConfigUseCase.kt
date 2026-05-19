package me.amirkazemzade.netwidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.amirkazemzade.netwidget.data.datasource.RemainedLocalDataSource
import me.amirkazemzade.netwidget.domain.models.DataDisplayMode
import me.amirkazemzade.netwidget.domain.models.RemainedWidgetConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRemainedWidgetConfigUseCase @Inject constructor(
    private val remainedLocalDataSource: RemainedLocalDataSource,
) {
    operator fun invoke(): Flow<RemainedWidgetConfig> {
        return remainedLocalDataSource
            .dataDisplayMode
            .map { displayMode ->
                RemainedWidgetConfig(dataDisplayMode = displayMode ?: DataDisplayMode.PERCENTAGE)
            }
    }
}