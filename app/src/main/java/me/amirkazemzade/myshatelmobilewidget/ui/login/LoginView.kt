package me.amirkazemzade.myshatelmobilewidget.ui.login

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valentinilk.shimmer.shimmer
import me.amirkazemzade.myshatelmobilewidget.R
import me.amirkazemzade.myshatelmobilewidget.domain.models.AuthenticatedResult
import me.amirkazemzade.myshatelmobilewidget.domain.models.CaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelMobileWidgetTheme
import kotlin.io.encoding.Base64

@Composable
fun LoginView(
    loginViewModel: LoginViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var captcha by remember { mutableStateOf("") }

    val captchaState by loginViewModel.captchaState.collectAsStateWithLifecycle()
    val loginRequestState by loginViewModel.loginRequestState.collectAsStateWithLifecycle()

    LaunchedEffect(loginRequestState) {
        if (loginRequestState is RequestStatus.Success) {
            Toast.makeText(context, "Login Requested Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(320.dp)
            ) {
                OutlinedTextField(
                    value = username,
                    label = { Text("Username") },
                    shape = RoundedCornerShape(16.dp),
                    onValueChange = { username = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Box(modifier = Modifier.height(16.dp))

                CaptchaRow(
                    captchaState = captchaState,
                    onFetchCaptcha = { loginViewModel.fetchCaptcha() },
                )
                OutlinedTextField(
                    value = captcha,
                    label = { Text("Captcha") },
                    shape = RoundedCornerShape(16.dp),
                    onValueChange = { captcha = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Box(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { loginViewModel.loginRequest(username, captcha) },
                    modifier = Modifier
                        .height(52.dp)
                        .fillMaxWidth()
                ) {
                    Text("Login Request")
                }
            }
        }
    }
}

@Composable
fun CaptchaRow(
    captchaState: RequestStatus<AuthenticatedResult<CaptchaBase64>>,
    onFetchCaptcha: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CaptchaImage(
            captchaState,
            modifier = Modifier
                .weight(1f)
                .height(52.dp)
                .background(Color(0xffeeeeee), MaterialTheme.shapes.large)
        )
        Box(modifier = Modifier.width(8.dp))
        FilledTonalIconButton(
            enabled = captchaState !is RequestStatus.Loading,
            onClick = onFetchCaptcha,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .size(52.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.rounded_refresh_24),
                "Refresh",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CaptchaImage(
    state: RequestStatus<AuthenticatedResult<CaptchaBase64>>,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is RequestStatus.Error -> Text("Error", modifier = modifier)

        RequestStatus.Loading -> Box(
            modifier = modifier
                .shimmer()
                .background(
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.large
                )
        )

        is RequestStatus.Success<AuthenticatedResult<CaptchaBase64>> -> Image(
            bitmap = state.data.data.value.toImageBitmap(),
            contentDescription = "Captcha",
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    MyShatelMobileWidgetTheme {
        LoginView()
    }
}

fun String.toImageBitmap(): ImageBitmap {
    val decodedBase64 = Base64.decode(this)
    val decodedImage = BitmapFactory.decodeByteArray(decodedBase64, 0, decodedBase64.size)
    return decodedImage.asImageBitmap()
}