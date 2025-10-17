package me.amirkazemzade.myshatelmobilewidget.ui.login.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import me.amirkazemzade.myshatelmobilewidget.R
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelDimensions
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelMobileWidgetTheme

@Composable
fun LoginPasswordView(
    username: String,
    onLogin: (password: String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {

    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }


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
            value = password,
            label = { Text("Password") },
            onValueChange = { password = it },
            shape = MaterialTheme.shapes.large,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onSend = { onLogin(password) }
            ),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    val id =
                        if (showPassword) R.drawable.rounded_visibility_24 else R.drawable.rounded_visibility_off_24
                    val contentDescription = if (showPassword) "Hide password" else "Show password"
                    Icon(painter = painterResource(id), contentDescription = contentDescription)
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Box(modifier = Modifier.height(MyShatelDimensions.large))

        FieldButton(
            isLoading = isLoading,
            text = "Login",
            onClick = { onLogin(password) },
        )
    }
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
