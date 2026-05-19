package me.amirkazemzade.netwidget.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.netwidget.domain.models.AuthenticatedResult
import me.amirkazemzade.netwidget.domain.models.CaptchaBase64
import me.amirkazemzade.netwidget.domain.models.Cookie
import me.amirkazemzade.netwidget.domain.models.RequestStatus

interface AuthRepository {

    suspend fun checkLoginStatus(cookie: Cookie): Flow<RequestStatus<AuthenticatedResult<Unit>>>
    suspend fun getCaptcha(
        cookie: Cookie? = null,
    ): Flow<RequestStatus<AuthenticatedResult<CaptchaBase64>>>

    suspend fun requestLoginForUser(
        cookie: Cookie,
        username: String,
        captchaResult: String,
    ): Flow<RequestStatus<AuthenticatedResult<Unit>>>

    suspend fun login(
        cookie: Cookie,
        username: String,
        password: String,
        captchaResult: String,
    ): Flow<RequestStatus<AuthenticatedResult<Unit>>>
}