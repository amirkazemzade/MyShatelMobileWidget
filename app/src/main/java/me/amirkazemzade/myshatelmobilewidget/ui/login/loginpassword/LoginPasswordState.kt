package me.amirkazemzade.myshatelmobilewidget.ui.login.loginpassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginPasswordState {
    private var _password by mutableStateOf("")
    val password: String
        get() = _password

    private var _showPassword by mutableStateOf(false)
    val showPassword: Boolean
        get() = _showPassword


    var passwordError by mutableStateOf<String?>(null)

    fun validate(): Boolean {
        passwordError = if (password.isBlank()) "Password is required" else null

        return passwordError == null
    }

    fun reverseShowPassword() {
        _showPassword = !showPassword
    }

    fun setPassword(value: String) {
        _password = value
    }
}