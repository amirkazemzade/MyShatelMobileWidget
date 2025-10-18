package me.amirkazemzade.myshatelmobilewidget.ui.login.loginrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.amirkazemzade.myshatelmobilewidget.R
import me.amirkazemzade.myshatelmobilewidget.domain.models.CaptchaBase64
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status

@Composable
fun CaptchaRow(
    captchaState: Status<CaptchaBase64>,
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
