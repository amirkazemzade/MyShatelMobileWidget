package me.amirkazemzade.myshatelmobilewidget.data.api

import me.amirkazemzade.myshatelmobilewidget.data.models.requests.LoginWithPasswordBody
import me.amirkazemzade.myshatelmobilewidget.data.models.requests.RequestLoginBody
import me.amirkazemzade.myshatelmobilewidget.data.models.responses.CaptchaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApi {

    @GET("/fiscal/id")
    suspend fun getFiscalId(
        @Header("Cookie") cookie: String?,
    ): Response<Unit>

    @GET("/captcha/refresh")
    suspend fun captcha(
        @Header("Cookie") cookie: String?,
    ): Response<CaptchaResponse>

    @POST("/login/username")
    suspend fun requestLogin(
        @Header("Cookie") cookie: String?,
        @Body body: RequestLoginBody,
    ): Response<Unit>

    @POST("/login/password")
    suspend fun login(
        @Header("Cookie") cookie: String?,
        @Body body: LoginWithPasswordBody,
    ): Response<Unit>
}