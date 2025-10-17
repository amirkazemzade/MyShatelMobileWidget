package me.amirkazemzade.myshatelmobilewidget.data

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.serialization.json.Json
import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository

class CookieRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
) : CookieRepository {

    companion object {
        private const val KEY_COOKIE = "cookie"
    }

    override fun getCookie(): Cookie? {
        val encodedValue = sharedPreferences.getString(KEY_COOKIE, null) ?: return null
        return Json.decodeFromString<Cookie>(encodedValue)
    }

    override fun setCookie(cookie: Cookie) {
        sharedPreferences.edit { putString(KEY_COOKIE, Json.encodeToString(cookie)) }
    }
}
