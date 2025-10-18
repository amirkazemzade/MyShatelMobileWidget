package me.amirkazemzade.myshatelmobilewidget.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import me.amirkazemzade.myshatelmobilewidget.domain.models.Cookie
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository

class CookieRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : CookieRepository {

    companion object {
        private val KEY_COOKIE = stringPreferencesKey("cookie")
        private val KEY_LOGIN_STATE = booleanPreferencesKey("loginState")
    }

    override suspend fun getCookie(): Cookie? {
        return dataStore.data.first()[KEY_COOKIE]?.decodeCookie()
    }

    override fun getCookieFlow(): Flow<Cookie?> {
        return dataStore.data.map { preferences ->
            preferences[KEY_COOKIE]?.decodeCookie()
        }
    }


    override suspend fun setCookie(cookie: Cookie) {
        dataStore.edit {
            it[KEY_COOKIE] = cookie.encodeCookie()
        }
    }

    override suspend fun setLoginState(state: Boolean) {
        dataStore.edit {
            it[KEY_LOGIN_STATE] = state
        }
    }

    override suspend fun isLoggedIn(): Boolean {
        return dataStore.data.first()[KEY_LOGIN_STATE] ?: false
    }

    override fun isLoggedInFlow(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[KEY_LOGIN_STATE] ?: false
        }
    }

    override suspend fun clearData() {
        dataStore.edit {
            it.remove(KEY_COOKIE)
            it.remove(KEY_LOGIN_STATE)
        }
    }

    private fun String.decodeCookie(): Cookie = Json.decodeFromString<Cookie>(this)
    private fun Cookie.encodeCookie(): String = Json.encodeToString(this)
}
