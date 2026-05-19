package me.amirkazemzade.netwidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.netwidget.domain.models.RequestStatus
import me.amirkazemzade.netwidget.domain.repositories.AuthRepository
import me.amirkazemzade.netwidget.domain.repositories.CookieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginWithPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    cookieRepository: CookieRepository,
) : GeneralUseCase<Unit>(cookieRepository, "RequestLoginUseCase") {
    operator fun invoke(
        username: String,
        password: String,
        captchaResult: String,
    ): Flow<RequestStatus<Unit>> =
        handleAuthenticatedRequestWithCookie { cookie ->
            authRepository.login(cookie, username, password, captchaResult)
        }
}
