package me.amirkazemzade.myshatelmobilewidget.ui.config.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import me.amirkazemzade.myshatelmobilewidget.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetConfigTopAppBar(
    onNavigateBack: () -> Unit,
    onSaveConfig: () -> Unit,
    showNavIcon: Boolean,
    actionEnabled: Boolean = true,
) {

    TopAppBar(
        title = {
            Text(stringResource(R.string.customize_widget))
        },
        navigationIcon = {
            if (showNavIcon) {
                IconButton(
                    onClick = onNavigateBack
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rounded_arrow_back_24),
                        contentDescription = stringResource(R.string.navigate_back)
                    )
                }
            }
        },
        actions = {
            TextButton(
                enabled = actionEnabled,
                onClick = {
                    onSaveConfig()
                }
            ) {
                Text(stringResource(R.string.save))
            }
        }
    )
}