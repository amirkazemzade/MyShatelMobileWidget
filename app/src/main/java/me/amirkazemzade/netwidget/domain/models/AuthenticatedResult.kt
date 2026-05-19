package me.amirkazemzade.netwidget.domain.models

data class AuthenticatedResult<T>(
    val data: T,
    val cookie: Cookie?,
)