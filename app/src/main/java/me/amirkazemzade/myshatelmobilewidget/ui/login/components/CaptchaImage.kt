package me.amirkazemzade.myshatelmobilewidget.ui.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.valentinilk.shimmer.shimmer
import me.amirkazemzade.myshatelmobilewidget.domain.models.CaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
import me.amirkazemzade.myshatelmobilewidget.ui.login.toImageBitmap

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

        is RequestStatus.Error -> Text("Error", modifier = modifier)


        is RequestStatus.Success<CaptchaBase64> -> Image(
            bitmap = state.data.value.toImageBitmap(),
            contentDescription = "Captcha",
            modifier = modifier,
        )
    }
}
