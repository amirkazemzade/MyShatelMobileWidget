package me.amirkazemzade.netwidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.netwidget.domain.models.CaptchaBase64
import me.amirkazemzade.netwidget.domain.models.RequestStatus
import me.amirkazemzade.netwidget.domain.repositories.AuthRepository
import me.amirkazemzade.netwidget.domain.repositories.CookieRepository
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "RequestLoginUseCase"

@Singleton
class FetchCaptchaUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    cookieRepository: CookieRepository,
) : GeneralUseCase<CaptchaBase64>(cookieRepository, TAG) {

    operator fun invoke(): Flow<RequestStatus<CaptchaBase64>> =
        handleAuthenticatedRequestWithNullableCookie { cookie -> authRepository.getCaptcha(cookie) }
}