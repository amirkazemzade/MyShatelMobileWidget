package me.amirkazemzade.netwidget.ui.login.loginpassword

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf

class LoginPasswordState {
    private var _password by mutableStateOf("")
    val password: String
        get() = _password

    private var _showPassword by mutableStateOf(false)
    val showPassword: Boolean
        get() = _showPassword


    var passwordError by mutableStateOf<String?>(null)

    fun validate(passwordRequiredMessage: String): Boolean {
        passwordError = if (password.isBlank()) passwordRequiredMessage else null

        return passwordError == null
    }

    fun reverseShowPassword() {
        _showPassword = !showPassword
    }

    fun setPassword(value: String) {
        _password = value
    }
}

val LoginPasswordStateSaver = Saver<LoginPasswordState, Bundle>(
    save = { state ->
        bundleOf(
            "password" to state.password,
            "passwordError" to state.passwordError
        )
    },
    restore = { bundle ->
        LoginPasswordState().apply {
            setPassword(bundle.getString("password", ""))
            passwordError = (bundle.getString("passwordError", ""))
        }
    }
)
