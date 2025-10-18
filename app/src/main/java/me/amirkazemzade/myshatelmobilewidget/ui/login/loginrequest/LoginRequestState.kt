package me.amirkazemzade.myshatelmobilewidget.ui.login.loginrequest

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginRequestState {
    private var _username by mutableStateOf("")
    val username: String
        get() = _username

    private var _captcha by mutableStateOf("")
    val captcha: String
        get() = _captcha
    var usernameError by mutableStateOf<String?>(null)
    var captchaError by mutableStateOf<String?>(null)

    fun validate(): Boolean {
        usernameError = if (username.isBlank()) "Username is required" else null
        captchaError = if (captcha.isBlank()) "Enter captcha" else null

        // Return true if there are no errors
        return usernameError == null && captchaError == null
    }

    fun setUsername(value: String) {
        _username = value
    }

    fun setCaptcha(value: String) {
        _captcha = value
    }
}