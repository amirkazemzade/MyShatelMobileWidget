package me.amirkazemzade.myshatelmobilewidget.ui.login.loginrequest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import me.amirkazemzade.myshatelmobilewidget.domain.models.CaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
import me.amirkazemzade.myshatelmobilewidget.ui.login.components.FieldButton
import me.amirkazemzade.myshatelmobilewidget.ui.login.components.FieldColumn
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelDimensions
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelMobileWidgetTheme

@Composable
fun LoginRequestView(
    captchaState: Status<CaptchaBase64>,
    onFetchCaptcha: () -> Unit,
    onLoginRequest: (username: String, captchaResult: String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    val loginRequestState = remember { LoginRequestState() }

    LoginRequestView(
        state = loginRequestState,
        captchaState = captchaState,
        onFetchCaptcha = onFetchCaptcha,
        onLoginRequest = onLoginRequest,
        modifier = modifier,
        isLoading = isLoading,
    )
}

@Composable
fun LoginRequestView(
    state: LoginRequestState,
    captchaState: Status<CaptchaBase64>,
    onFetchCaptcha: () -> Unit,
    onLoginRequest: (username: String, captchaResult: String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    val captchaFocusRequester = remember { FocusRequester() }

    FieldColumn(modifier = modifier) {
        OutlinedTextField(
            value = state.username,
            label = { Text("Username") },
            shape = MaterialTheme.shapes.large,
            onValueChange = state::setUsername,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(onNext = { captchaFocusRequester.requestFocus() }),
            singleLine = true,
            isError = state.usernameError != null,
            supportingText = {
                if (state.usernameError != null) {
                    Text(state.usernameError!!)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.height(MyShatelDimensions.medium))

        CaptchaRow(
            captchaState = captchaState,
            onFetchCaptcha = onFetchCaptcha,
        )
        OutlinedTextField(
            value = state.captcha,
            label = { Text("Captcha") },
            shape = MaterialTheme.shapes.large,
            onValueChange = state::setCaptcha,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { callOnLoginRequest(state, onLoginRequest) }
            ),
            singleLine = true,
            isError = state.captchaError != null,
            supportingText = {
                if (state.captchaError != null) {
                    Text(state.captchaError!!)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(captchaFocusRequester)
        )

        Box(modifier = Modifier.height(MyShatelDimensions.large))

        FieldButton(
            text = "Continue",
            enabled = !isLoading,
            onClick = { callOnLoginRequest(state, onLoginRequest) },
        )
    }
}

private fun callOnLoginRequest(
    state: LoginRequestState,
    onLoginRequest: (String, String) -> Unit,
) {
    if (!state.validate()) return
    onLoginRequest(state.username, state.captcha)
}


@Preview
@Composable
fun LoginRequestViewPreview() {
    MyShatelMobileWidgetTheme {
        Surface {
            LoginRequestView(
                captchaState = Status.Idle,
                onFetchCaptcha = {},
                onLoginRequest = { _, _ -> },
                isLoading = false
            )
        }
    }
}
