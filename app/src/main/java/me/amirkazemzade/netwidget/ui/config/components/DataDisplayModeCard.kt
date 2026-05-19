package me.amirkazemzade.netwidget.ui.config.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.amirkazemzade.netwidget.R
import me.amirkazemzade.netwidget.domain.models.DataDisplayMode

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
fun DataDisplayModeCard(
    selectedMode: DataDisplayMode,
    onSelectMode: (DataDisplayMode) -> Unit,
    enabled: Boolean = true,
) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = stringResource(R.string.data_display_mode),
            style = MaterialTheme.typography.titleLargeEmphasized,
            modifier = Modifier.padding(16.dp)
        )
        DataDisplayModeRadioButton(
            enabled = enabled,
            label = stringResource(R.string.percentage),
            displayMode = DataDisplayMode.PERCENTAGE,
            selectedDisplayMode = selectedMode,
            onSelect = onSelectMode,
        )
        DataDisplayModeRadioButton(
            enabled = enabled,
            label = stringResource(R.string.traffic),
            displayMode = DataDisplayMode.TRAFFIC,
            selectedDisplayMode = selectedMode,
            onSelect = onSelectMode,
        )
    }
}