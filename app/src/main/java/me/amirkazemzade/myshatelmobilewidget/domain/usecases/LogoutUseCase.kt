package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val cookieRepository: CookieRepository,
) : GeneralUseCase<Unit>(cookieRepository, "CheckAuthUseCase") {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<RequestStatus<Unit>> = handleRequest {
        cookieRepository.clearData()
    }
}