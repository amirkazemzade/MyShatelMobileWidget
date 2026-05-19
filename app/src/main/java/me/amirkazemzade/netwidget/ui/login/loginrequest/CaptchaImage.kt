package me.amirkazemzade.netwidget.ui.login.loginrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.valentinilk.shimmer.shimmer
import me.amirkazemzade.netwidget.R
import me.amirkazemzade.netwidget.domain.models.CaptchaBase64
import me.amirkazemzade.netwidget.domain.models.RequestStatus
import me.amirkazemzade.netwidget.domain.models.Status
import me.amirkazemzade.netwidget.ui.login.toImageBitmap

@Composable
fun CaptchaImage(
    state: Status<CaptchaBase64>,
    modifier: Modifier = Modifier,
) {
    when (state) {
        RequestStatus.Loading, Status.Idle -> Box(
            modifier = modifier
                .shimmer()
                .background(
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.large
                )
        )

        is RequestStatus.Error -> Text(stringResource(R.string.error), modifier = modifier)


        is RequestStatus.Success<CaptchaBase64> -> Image(
            bitmap = state.data.value.toImageBitmap(),
            contentDescription = stringResource(R.string.captcha),
            modifier = modifier,
        )
    }
}
