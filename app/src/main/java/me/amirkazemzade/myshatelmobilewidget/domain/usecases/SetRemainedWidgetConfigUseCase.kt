package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkazemzade.myshatelmobilewidget.data.datasource.RemainedLocalDataSource
import me.amirkazemzade.myshatelmobilewidget.domain.models.RemainedWidgetConfig
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetRemainedWidgetConfigUseCase @Inject constructor(
    private val remainedLocalDataSource: RemainedLocalDataSource,
) {
    operator fun invoke(
        config: RemainedWidgetConfig,
    ): Flow<RequestStatus<Unit>> = flow {
        emit(RequestStatus.Loading)

        try {
            remainedLocalDataSource.setDataDisplayMode(config.dataDisplayMode)
            emit(RequestStatus.Success(Unit))
        } catch (e: Exception) {
            emit(RequestStatus.Error(message = e.localizedMessage ?: "Something went wrong"))

        }
    }
}