package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.InvalidAuthentication
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.AuthRepository
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository
import javax.inject.Inject

class CheckAuthUseCase @Inject constructor(
    private val cookieRepository: CookieRepository,
    private val authRepository: AuthRepository,
) : GeneralUseCase<Unit>(cookieRepository, "CheckAuthUseCase") {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<RequestStatus<Unit>> = handleAuthenticatedRequest {
        val authStateFlow = combine(
            cookieRepository.getCookieFlow(),
            cookieRepository.isLoggedInFlow()
        ) { cookie, isLoggedIn ->
            cookie to isLoggedIn
        }

        authStateFlow.flatMapLatest { (cookie, isLoggedIn) ->
            if (!isLoggedIn || cookie == null) {
                flowOf(RequestStatus.Error(InvalidAuthentication()))
            } else {
                authRepository.checkLoginStatus(cookie)
            }
        }
    }
}