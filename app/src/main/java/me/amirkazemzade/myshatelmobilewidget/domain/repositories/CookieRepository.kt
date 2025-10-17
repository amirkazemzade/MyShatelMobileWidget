package me.amirkazemzade.myshatelmobilewidget.domain.repositories

import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie

interface CookieRepository {
    fun getCookie(): Cookie?
    fun setCookie(cookie: Cookie)
}
