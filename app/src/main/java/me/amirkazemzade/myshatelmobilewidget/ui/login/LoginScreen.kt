package me.amirkazemzade.myshatelmobilewidget.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.ui.login.loginpassword.LoginPasswordView
import me.amirkazemzade.myshatelmobilewidget.ui.login.loginrequest.LoginRequestView
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelMobileWidgetTheme

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val captchaState by loginViewModel.captchaState.collectAsStateWithLifecycle()
    val loginRequestState by loginViewModel.loginRequestState.collectAsStateWithLifecycle()
    val loginWithPasswordState by loginViewModel.loginWithPasswordState.collectAsStateWithLifecycle()

    LaunchedEffect(loginRequestState) {
        if (loginRequestState is RequestStatus.Success) {
            Toast.makeText(context, "Login Requested Successfully", Toast.LENGTH_SHORT).show()
        }
        if (loginRequestState is RequestStatus.Error) {
            Toast.makeText(
                context,
                (loginRequestState as RequestStatus.Error).message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    LaunchedEffect(loginWithPasswordState) {
        if (loginWithPasswordState is RequestStatus.Success) {
            Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT).show()
        }
        if (loginWithPasswordState is RequestStatus.Error) {
            Toast.makeText(
                context,
                (loginWithPasswordState as RequestStatus.Error).message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Scaffold { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            val currentLoginRequestState = loginRequestState
            if (currentLoginRequestState is RequestStatus.Success) {
                LoginPasswordView(
                    username = currentLoginRequestState.data.username,
                    isLoading = loginWithPasswordState is RequestStatus.Loading,
                    onLogin = { password ->
                        loginViewModel.loginWithPassword(password)
                    }
                )
            } else {
                LoginRequestView(
                    captchaState = captchaState,
                    isLoading = currentLoginRequestState is RequestStatus.Loading,
                    onFetchCaptcha = { loginViewModel.fetchCaptcha() },
                    onLoginRequest = loginViewModel::loginRequest
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    MyShatelMobileWidgetTheme {
        LoginScreen()
    }
}
