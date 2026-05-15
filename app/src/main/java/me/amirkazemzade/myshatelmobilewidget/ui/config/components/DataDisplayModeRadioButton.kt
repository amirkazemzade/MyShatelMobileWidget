package me.amirkazemzade.myshatelmobilewidget.ui.config.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.amirkazemzade.myshatelmobilewidget.domain.models.DataDisplayMode

@Composable
fun DataDisplayModeRadioButton(
    label: String,
    displayMode: DataDisplayMode,
    selectedDisplayMode: DataDisplayMode,
    onSelect: (DataDisplayMode) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth()
            .clickable(enabled = enabled) {
                onSelect(displayMode)
            }
            .padding(horizontal = 8.dp)
    ) {
        RadioButton(
            enabled = enabled,
            selected = selectedDisplayMode == displayMode,
            onClick = { onSelect(displayMode) },
        )
        Text(
            text = label
        )
    }
}