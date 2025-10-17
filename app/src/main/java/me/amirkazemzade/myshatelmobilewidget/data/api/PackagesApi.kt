package me.amirkazemzade.myshatelmobilewidget.data.api

import me.amirkazemzade.myshatelmobilewidget.data.models.responses.PackagesResponse
import me.amirkazemzade.myshatelmobilewidget.data.models.responses.RemainedResponse
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface PackagesApi {
    @POST("/packages")
    suspend fun getPackages(
        @Header("Cookie") cookie: String,
    ): Response<PackagesResponse>

    @POST("/remained")
    suspend fun getRemained(
        @Header("Cookie") cookie: String,
    ): Response<RemainedResponse>
}