package me.amirkazemzade.myshatelmobilewidget.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkazemzade.myshatelmobilewidget.data.api.AuthApi
import me.amirkazemzade.myshatelmobilewidget.data.converters.toCaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.data.converters.toCookie
import me.amirkazemzade.myshatelmobilewidget.data.converters.toHeaderValue
import me.amirkazemzade.myshatelmobilewidget.data.models.requests.LoginWithPasswordBody
import me.amirkazemzade.myshatelmobilewidget.data.models.requests.RequestLoginBody
import me.amirkazemzade.myshatelmobilewidget.domain.models.AuthenticatedResult
import me.amirkazemzade.myshatelmobilewidget.domain.models.CaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authApi: AuthApi) : AuthRepository {
    override suspend fun getCaptcha(
        cookie: Cookie?,
    ): Flow<RequestStatus<AuthenticatedResult<CaptchaBase64>>> =
        flow {
            emit(RequestStatus.Loading)
            val response = authApi.captcha(cookie = cookie?.toHeaderValue())
            if (response.isSuccessful) {
                val data = response.body()!!.toCaptchaBase64()
                val cookie = response.headers()["Set-Cookie"]!!.toCookie()
                emit(RequestStatus.Success(AuthenticatedResult(data, cookie)))
            } else {
                emit(RequestStatus.Error(response.message()))
            }
        }

    override suspend fun requestLoginForUser(
        cookie: Cookie,
        username: String,
        captchaResult: String,
    ): Flow<RequestStatus<AuthenticatedResult<Unit>>> = flow {
        emit(RequestStatus.Loading)
        val response = authApi.requestLogin(
            cookie = cookie.toHeaderValue(),
            body = RequestLoginBody(
                username = username,
                captcha = captchaResult,
            )
        )
        if (response.isSuccessful) {
            emit(RequestStatus.Success(AuthenticatedResult(Unit, cookie)))
        } else {
            emit(RequestStatus.Error(response.message()))
        }
    }

    override suspend fun login(
        cookie: Cookie,
        username: String,
        password: String,
        captchaResult: String,
    ): Flow<RequestStatus<AuthenticatedResult<Unit>>> = flow {
        emit(RequestStatus.Loading)

        val response = authApi.login(
            cookie = cookie.toHeaderValue(),
            body = LoginWithPasswordBody(
                username = username,
                password = password,
                captcha = captchaResult,
            )
        )

        if (response.isSuccessful) {
            emit(RequestStatus.Success(AuthenticatedResult(Unit, cookie)))
        } else {
            emit(RequestStatus.Error(response.message()))
        }
    }
}