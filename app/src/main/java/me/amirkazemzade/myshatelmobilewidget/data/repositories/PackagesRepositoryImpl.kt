package me.amirkazemzade.myshatelmobilewidget.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.amirkazemzade.myshatelmobilewidget.data.api.PackagesApi
import me.amirkazemzade.myshatelmobilewidget.data.converters.toHeaderValue
import me.amirkazemzade.myshatelmobilewidget.data.converters.toPackagesArray
import me.amirkazemzade.myshatelmobilewidget.data.converters.toRemained
import me.amirkazemzade.myshatelmobilewidget.domain.models.AuthenticatedResult
import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie
import me.amirkazemzade.myshatelmobilewidget.domain.models.InternetPackage
import me.amirkazemzade.myshatelmobilewidget.domain.models.Remained
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.PackagesRepository

class PackagesRepositoryImpl(private val packagesApi: PackagesApi) : PackagesRepository {
    override suspend fun getPackages(cookie: Cookie): Flow<RequestStatus<AuthenticatedResult<Array<InternetPackage>>>> =
        flow {
            emit(RequestStatus.Loading)
            val response = packagesApi.getPackages(cookie = cookie.toHeaderValue())
            if (response.isSuccessful) {
                val data = response.body()!!.toPackagesArray()
                emit(RequestStatus.Success(AuthenticatedResult(data, cookie)))
            } else {
                emit(RequestStatus.Error(response.message()))
            }
        }

    override suspend fun getRemained(cookie: Cookie): Flow<RequestStatus<AuthenticatedResult<Remained>>> =
        flow {
            emit(RequestStatus.Loading)
            val response = packagesApi.getRemained(cookie = cookie.toHeaderValue())
            if (response.isSuccessful) {
                val data = response.body()!!.toRemained()
                emit(RequestStatus.Success(AuthenticatedResult(data, cookie)))
            } else {
                emit(RequestStatus.Error(response.message()))
            }
        }
}