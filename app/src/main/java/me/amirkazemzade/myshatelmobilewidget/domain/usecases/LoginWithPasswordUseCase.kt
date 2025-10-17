package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.InvalidAuthentication
import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.InvalidAuthenticationType
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.AuthRepository
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository
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
        handleRequest { cookie ->
            if (cookie == null) throw InvalidAuthentication(type = InvalidAuthenticationType.LocalCookieNotFound)
            authRepository.login(cookie, username, password, captchaResult)
        }
}
