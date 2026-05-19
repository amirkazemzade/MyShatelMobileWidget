package me.amirkazemzade.netwidget.domain.exceptions

class UnhandledRedirectException(redirectPath: String?) :
    AppException("Unknown redirect path:$redirectPath")