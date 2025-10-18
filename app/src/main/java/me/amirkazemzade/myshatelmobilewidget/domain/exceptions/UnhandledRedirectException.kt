package me.amirkazemzade.myshatelmobilewidget.domain.exceptions

class UnhandledRedirectException(redirectPath: String?) :
    AppException("Unknown redirect path:$redirectPath")