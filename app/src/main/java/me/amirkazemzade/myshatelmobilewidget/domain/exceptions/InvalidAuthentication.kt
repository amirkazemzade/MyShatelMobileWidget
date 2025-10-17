package me.amirkazemzade.myshatelmobilewidget.domain.exceptions

class InvalidAuthentication(type: InvalidAuthenticationType) :
    AppException("Authentication failed due to $type")

enum class InvalidAuthenticationType {
    LocalCookieNotFound,
    ServerAuthenticationFailed,
}