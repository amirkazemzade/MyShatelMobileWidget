package me.amirkazemzade.myshatelmobilewidget.domain.usecases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.AppException
import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.InvalidAuthentication
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
    fun handleAuthenticatedRequestWithCookie(request: suspend (cookie: Cookie?) -> Flow<RequestStatus<AuthenticatedResult<T>>>): Flow<RequestStatus<T>> =
        handleAuthenticatedRequestWithCookie(transform = { it }, request = request)

    fun handleAuthenticatedRequest(request: suspend () -> Flow<RequestStatus<AuthenticatedResult<T>>>): Flow<RequestStatus<T>> =
        handleAuthenticatedRequest(transform = { it }, request = request)

    fun <R> handleAuthenticatedRequestWithCookie(
        transform: (T) -> R,
        request: suspend (cookie: Cookie?) -> Flow<RequestStatus<AuthenticatedResult<T>>>,
    ): Flow<RequestStatus<R>> =
        handleAuthenticatedRequest(transform, request = { request(cookieRepository.getCookie()) })

    fun <R> handleAuthenticatedRequest(
        transform: (T) -> R,
        request: suspend () -> Flow<RequestStatus<AuthenticatedResult<T>>>,
    ): Flow<RequestStatus<R>> =
        flow {
            request()
                .onEach { result ->
                    when (result) {
                        is Success<AuthenticatedResult<T>> if (result.data.cookie != null) -> {
                            cookieRepository.setCookie(result.data.cookie)
                            emit(Success(transform(result.data.data)))
                        }

                        is Success<AuthenticatedResult<T>> -> {
                            emit(Error(InvalidAuthentication()))
                        }

                        is Error -> emit(Error(result.message, result.statusCode))
                        Loading -> emit(Loading)
                    }
                }
                .collect()
        }.handleExceptions()

    fun handleRequest(
        request: suspend () -> T,
    ): Flow<RequestStatus<T>> =
        flow {
            emit(Loading)
            val result = request()
            emit(Success(result))
        }.handleExceptions()


    private fun <R> Flow<RequestStatus<R>>.handleExceptions(): Flow<RequestStatus<R>> =
        catch { exception ->
            if (exception is AppException) {
                emit(Error(exception.errorMessage))
                return@catch
            }
            Log.e(tag, exception.message, exception)
            emit(Error("Something went wrong"))
        }
}