package me.amirkazemzade.myshatelmobilewidget.ui.login.loginpassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import me.amirkazemzade.myshatelmobilewidget.R
import me.amirkazemzade.myshatelmobilewidget.ui.login.components.FieldButton
import me.amirkazemzade.myshatelmobilewidget.ui.login.components.FieldColumn
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelDimensions
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelMobileWidgetTheme

@Composable
fun LoginPasswordView(
    username: String,
    onLogin: (password: String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {

    val state = remember { LoginPasswordState() }

    LoginPasswordView(
        state = state,
        username = username,
        onLogin = onLogin,
        modifier = modifier,
        isLoading = isLoading,
    )
}

@Composable
fun LoginPasswordView(
    state: LoginPasswordState,
    username: String,
    onLogin: (password: String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {

    FieldColumn(modifier = modifier) {
        OutlinedTextField(
            value = username,
            label = { Text("Username") },
            shape = MaterialTheme.shapes.large,
            readOnly = true,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.height(MyShatelDimensions.medium))

        OutlinedTextField(
            value = state.password,
            label = { Text("Password") },
            onValueChange = state::setPassword,
            shape = MaterialTheme.shapes.large,
            visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onSend = { callOnLogin(state = state, onLogin = onLogin) }
            ),
            trailingIcon = {
                IconButton(onClick = state::reverseShowPassword) {
                    val id =
                        if (state.showPassword) R.drawable.rounded_visibility_24 else R.drawable.rounded_visibility_off_24
                    val contentDescription =
                        if (state.showPassword) "Hide password" else "Show password"
                    Icon(painter = painterResource(id), contentDescription = contentDescription)
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Box(modifier = Modifier.height(MyShatelDimensions.large))

        FieldButton(
            isLoading = isLoading,
            text = "Login",
            onClick = {
                callOnLogin(state = state, onLogin = onLogin)
            },
        )
    }

}

private fun callOnLogin(
    state: LoginPasswordState,
    onLogin: (String) -> Unit,
) {
    if (!state.validate()) return
    onLogin(state.password)
}


@PreviewLightDark
@Composable
private fun LoginPasswordViewPreview() {
    MyShatelMobileWidgetTheme {
        Surface {
            LoginPasswordView(
                username = "123456789",
                onLogin = {}
            )
        }
    }
}
