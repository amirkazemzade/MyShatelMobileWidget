package me.amirkazemzade.netwidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.netwidget.domain.models.LoginRequest
import me.amirkazemzade.netwidget.domain.models.RequestStatus
import me.amirkazemzade.netwidget.domain.repositories.AuthRepository
import me.amirkazemzade.netwidget.domain.repositories.CookieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    cookieRepository: CookieRepository,
) : GeneralUseCase<Unit>(cookieRepository, "RequestLoginUseCase") {
    operator fun invoke(
        loginRequest: LoginRequest,
    ): Flow<RequestStatus<LoginRequest>> =
        handleAuthenticatedRequestWithCookie(transform = { loginRequest }) { cookie ->
            authRepository.requestLoginForUser(
                cookie,
                loginRequest.username,
                loginRequest.captchaResult,
            )
        }
}
