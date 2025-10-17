package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.AppException
import me.amirkazemzade.myshatelmobilewidget.domain.models.AuthenticatedResult
import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus.Error
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus.Loading
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus.Success
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository

abstract class GeneralUseCase<T>(
    private val cookieRepository: CookieRepository,
    private val tag: String,
) {
    fun handleRequest(request: suspend (cookie: Cookie?) -> Flow<RequestStatus<AuthenticatedResult<T>>>): Flow<RequestStatus<T>> =
        handleRequest(transform = { it }, request = request)

    fun <R> handleRequest(
        transform: (T) -> R,
        request: suspend (cookie: Cookie?) -> Flow<RequestStatus<AuthenticatedResult<T>>>,
    ): Flow<RequestStatus<R>> =
        flow {
            val cookie = cookieRepository.getCookie()
            request(cookie)
                .onEach { result ->
                    when (result) {
                        is Success<AuthenticatedResult<T>> -> {
                            cookieRepository.setCookie(result.data.cookie)
                            emit(Success(transform(result.data.data)))
                        }

                        is Error -> emit(Error(result.message))
                        Loading -> emit(Loading)
                    }
                }
                .collect()
        }.catch {
            if (it is AppException) {
                emit(Error(it.errorMessage))
                return@catch
            }
            Log.e(tag, it.message, it)
            emit(Error("Something went wrong"))
        }
}