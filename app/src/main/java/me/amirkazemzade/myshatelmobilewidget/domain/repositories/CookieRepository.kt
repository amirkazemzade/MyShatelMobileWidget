package me.amirkazemzade.myshatelmobilewidget.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie

interface CookieRepository {
    suspend fun getCookie(): Cookie?
    fun getCookieFlow(): Flow<Cookie?>
    suspend fun setCookie(cookie: Cookie)
    suspend fun setLoginState(state: Boolean)

    suspend fun isLoggedIn(): Boolean

    fun isLoggedInFlow(): Flow<Boolean>

    suspend fun clearData()
}
