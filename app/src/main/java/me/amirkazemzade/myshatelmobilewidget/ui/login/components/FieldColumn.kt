package me.amirkazemzade.myshatelmobilewidget.ui.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelDimensions

@Composable
fun FieldColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .width(MyShatelDimensions.fieldWidth),
        content = content,
    )
}