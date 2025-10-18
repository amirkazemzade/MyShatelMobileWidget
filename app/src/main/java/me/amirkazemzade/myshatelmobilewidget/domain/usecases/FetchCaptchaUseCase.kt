package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.myshatelmobilewidget.domain.models.CaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.AuthRepository
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "RequestLoginUseCase"

@Singleton
class FetchCaptchaUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    cookieRepository: CookieRepository,
) : GeneralUseCase<CaptchaBase64>(cookieRepository, TAG) {

    operator fun invoke(): Flow<RequestStatus<CaptchaBase64>> =
        handleAuthenticatedRequestWithCookie { cookie -> authRepository.getCaptcha(cookie) }
}