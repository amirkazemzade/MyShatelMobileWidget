package me.amirkazemzade.netwidget.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.netwidget.domain.models.AuthenticatedResult
import me.amirkazemzade.netwidget.domain.models.Cookie
import me.amirkazemzade.netwidget.domain.models.InternetPackage
import me.amirkazemzade.netwidget.domain.models.Remained
import me.amirkazemzade.netwidget.domain.models.RequestStatus

interface PackagesRepository {
    fun getPackages(cookie: Cookie): Flow<RequestStatus<AuthenticatedResult<Array<InternetPackage>>>>

    fun getRemained(cookie: Cookie): Flow<RequestStatus<AuthenticatedResult<Remained>>>
}