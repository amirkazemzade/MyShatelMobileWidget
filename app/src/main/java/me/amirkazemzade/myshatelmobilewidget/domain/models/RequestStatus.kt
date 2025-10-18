package me.amirkazemzade.myshatelmobilewidget.domain.models

import me.amirkazemzade.myshatelmobilewidget.domain.exceptions.AppException

sealed interface RequestStatus<out T> : Status<T> {
    data object Loading : RequestStatus<Nothing>
    data class Success<T>(val data: T) : RequestStatus<T>
    data class Error(val message: String, val statusCode: Int? = null) : RequestStatus<Nothing> {
        constructor(appException: AppException) : this(appException.errorMessage)
    }
}