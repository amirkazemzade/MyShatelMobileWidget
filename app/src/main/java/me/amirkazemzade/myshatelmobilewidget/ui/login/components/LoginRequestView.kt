package me.amirkazemzade.myshatelmobilewidget.ui.login.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import me.amirkazemzade.myshatelmobilewidget.domain.models.CaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
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

    var username by remember { mutableStateOf("") }
    var captcha by remember { mutableStateOf("") }
    val captchaFocusRequester = remember { FocusRequester() }

    FieldColumn(modifier = modifier) {
        OutlinedTextField(
            value = username,
            label = { Text("Username") },
            shape = MaterialTheme.shapes.large,
            onValueChange = { username = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(onNext = { captchaFocusRequester.requestFocus() }),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.height(MyShatelDimensions.medium))

        CaptchaRow(
            captchaState = captchaState,
            onFetchCaptcha = onFetchCaptcha,
        )
        OutlinedTextField(
            value = captcha,
            label = { Text("Captcha") },
            shape = MaterialTheme.shapes.large,
            onValueChange = { captcha = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = { onLoginRequest(username, captcha) }),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(captchaFocusRequester)
        )

        Box(modifier = Modifier.height(MyShatelDimensions.large))

        FieldButton(
            text = "Continue",
            enabled = !isLoading,
            onClick = { onLoginRequest(username, captcha) },
        )
    }
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
