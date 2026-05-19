package me.amirkazemzade.netwidget.ui.dashboard.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.amirkazemzade.netwidget.R

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
fun DashboardTopAppBar(isLoggingOut: Boolean, onLogout: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.dashboard)) },
        actions = {
            val modifier = Modifier.size(40.dp)
            val color = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
            if (isLoggingOut) {
                LoadingIndicator(
                    modifier = modifier,
                    color = color
                )
            } else {
                IconButton(
                    onClick = onLogout,
                    modifier = modifier
                ) {
                    Icon(
                        painterResource(R.drawable.rounded_logout_24),
                        contentDescription = stringResource(R.string.logout),
                        tint = color,
                    )
                }
            }
        }
    )
}
