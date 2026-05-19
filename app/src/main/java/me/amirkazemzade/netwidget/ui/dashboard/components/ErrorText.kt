package me.amirkazemzade.netwidget.ui.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import me.amirkazemzade.netwidget.R

@Composable
fun ErrorText(
    error: String,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Companion.CenterVertically,
        modifier = modifier
    ) {
        Text(error)
        IconButton(onClick = onRetry) {
            Icon(
                painter = painterResource(R.drawable.rounded_refresh_24),
                contentDescription = stringResource(R.string.retry),
            )
        }
    }
}
