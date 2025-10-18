package me.amirkazemzade.myshatelmobilewidget.domain.models

data class AuthenticatedResult<T>(
    val data: T,
    val cookie: Cookie?,
)