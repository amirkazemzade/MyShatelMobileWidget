package me.amirkazemzade.netwidget.data.api

import me.amirkazemzade.netwidget.data.models.requests.RemainedRequestBody
import me.amirkazemzade.netwidget.data.models.responses.MsisdnsInfoResponseItem
import me.amirkazemzade.netwidget.data.models.responses.PackagesResponse
import me.amirkazemzade.netwidget.data.models.responses.RemainedResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PackagesApi {
    @POST("/packages")
    suspend fun getPackages(@Header("Cookie") cookie: String): Response<PackagesResponse>

    @POST("/remained")
    suspend fun getRemained(
        @Header("Cookie") cookie: String,
        @Body body: RemainedRequestBody,
    ): Response<RemainedResponse>

    @GET("/termination/msisdns")
    suspend fun getMsisdnsInfo(@Header("Cookie") cookie: String): Response<ArrayList<MsisdnsInfoResponseItem>>
}