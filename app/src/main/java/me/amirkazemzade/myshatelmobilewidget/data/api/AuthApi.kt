package me.amirkazemzade.myshatelmobilewidget.data.api

import me.amirkazemzade.myshatelmobilewidget.data.models.responses.CaptchaResponse
import me.amirkazemzade.myshatelmobilewidget.data.models.requests.RequestLoginBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthApi {
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
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("captcha") captchaResult: String,
    ): Response<Unit>
}