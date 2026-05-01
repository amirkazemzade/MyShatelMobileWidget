package me.amirkazemzade.myshatelmobilewidget.ui.login.loginrequest

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf

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

val LoginRequestStateSaver = Saver<LoginRequestState, Bundle>(
    save = { state ->
        bundleOf(
            "username" to state.username,
            "captcha" to state.captcha,
            "usernameError" to state.usernameError,
            "captchaError" to state.captchaError
        )
    },
    restore = { bundle ->
        LoginRequestState().apply {
            setUsername(bundle.getString("username", ""))
            setCaptcha(bundle.getString("captcha", ""))
            usernameError = bundle.getString("usernameError")
            captchaError = bundle.getString("captchaError")
        }
    }
)