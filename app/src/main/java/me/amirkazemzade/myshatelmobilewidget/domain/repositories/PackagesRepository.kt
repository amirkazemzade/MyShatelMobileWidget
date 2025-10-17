package me.amirkazemzade.myshatelmobilewidget.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.myshatelmobilewidget.domain.models.AuthenticatedResult
import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie
import me.amirkazemzade.myshatelmobilewidget.domain.models.InternetPackage
import me.amirkazemzade.myshatelmobilewidget.domain.models.Remained
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus

interface PackagesRepository {
    suspend fun getPackages(cookie: Cookie): Flow<RequestStatus<AuthenticatedResult<Array<InternetPackage>>>>

    suspend fun getRemained(cookie: Cookie): Flow<RequestStatus<AuthenticatedResult<Remained>>>
}