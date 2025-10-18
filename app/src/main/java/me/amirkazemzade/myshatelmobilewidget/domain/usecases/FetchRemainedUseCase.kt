package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.myshatelmobilewidget.domain.models.Remained
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.PackagesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchRemainedUseCase @Inject constructor(
    cookieRepository: CookieRepository,
    private val packagesRepository: PackagesRepository,
): GeneralUseCase<Remained>(cookieRepository, "FetchRemainedUseCase") {

    operator fun invoke(): Flow<RequestStatus<Remained>> = handleAuthenticatedRequestWithCookie { cookie ->
        packagesRepository.getRemained(cookie)
    }
}