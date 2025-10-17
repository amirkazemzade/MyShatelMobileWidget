package me.amirkazemzade.myshatelmobilewidget.domain.models

sealed interface RequestStatus<out T> {
    data object Loading : RequestStatus<Nothing>
    data class Success<T>(val data: T) : RequestStatus<T>
    data class Error(val message: String) : RequestStatus<Nothing>
}