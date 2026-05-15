package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.amirkazemzade.myshatelmobilewidget.data.datasource.RemainedLocalDataSource
import me.amirkazemzade.myshatelmobilewidget.domain.models.DataDisplayMode
import me.amirkazemzade.myshatelmobilewidget.domain.models.RemainedWidgetConfig
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