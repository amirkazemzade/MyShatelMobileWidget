package me.amirkazemzade.netwidget.ui.config

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.amirkazemzade.netwidget.domain.models.DataDisplayMode
import me.amirkazemzade.netwidget.ui.config.components.DataDisplayModeCard
import me.amirkazemzade.netwidget.ui.config.components.WidgetConfigTopAppBar
import me.amirkazemzade.netwidget.ui.config.components.WidgetPreviewBox
import me.amirkazemzade.netwidget.ui.theme.MyShatelMobileAppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WidgetConfigScreen(
    onUpdateWidget: () -> Unit,
    onNavigateBack: () -> Unit,
    showNavIcon: Boolean = false,
    viewModel: WidgetConfigViewModel = hiltViewModel(),
) {
    val configState by viewModel.currentConfigState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is WidgetConfigEvent.Error -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                WidgetConfigEvent.Success -> {
                    onUpdateWidget()
                }
            }
        }
    }

    WidgetConfigScreenLayout(
        configState = configState,
        showNavIcon = showNavIcon,
        onNavigateBack = onNavigateBack,
        onSaveConfig = { viewModel.save() },
        onSelectMode = { displayMode: DataDisplayMode ->
            viewModel.updateDataDisplayMode(displayMode)
        },
    )
}

@Composable
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
private fun WidgetConfigScreenLayout(
    configState: WidgetConfigUiState,
    showNavIcon: Boolean,
    onNavigateBack: () -> Unit,
    onSaveConfig: () -> Unit,
    onSelectMode: (DataDisplayMode) -> Unit,
) {
    Scaffold(
        topBar = {
            WidgetConfigTopAppBar(
                actionEnabled = !configState.isLoading && !configState.isSaving,
                showNavIcon = showNavIcon,
                onNavigateBack = onNavigateBack,
                onSaveConfig = onSaveConfig,
            )
        },
        containerColor = Color.Transparent,
    ) { paddingValues ->

        val modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()

        if (configState.isLoading) {
            Box(
                modifier = modifier
            ) {
                LoadingIndicator()
            }
        } else {
            WidgetConfigContent(
                isSaving = configState.isSaving,
                dataDisplayMode = configState.remainedWidgetConfig.dataDisplayMode,
                onSelectMode = onSelectMode,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun WidgetConfigContent(
    onSelectMode: (DataDisplayMode) -> Unit,
    modifier: Modifier = Modifier,
    isSaving: Boolean = false,
    dataDisplayMode: DataDisplayMode = DataDisplayMode.PERCENTAGE,
) {
    Column(
        modifier = modifier
    ) {

        Box(
            modifier = Modifier
                .height(4.dp)
        ) {
            if (isSaving) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        WidgetPreviewBox(selectedDataDisplayMode = dataDisplayMode)

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column {
                DataDisplayModeCard(
                    enabled = !isSaving,
                    selectedMode = dataDisplayMode,
                    onSelectMode = onSelectMode
                )
            }
        }
    }
}

@PreviewDynamicColors
@Composable
private fun WidgetConfigScreenPreview() {
    MyShatelMobileAppTheme {
        WidgetConfigContent(
            onSelectMode = {}
        )
    }
}

@PreviewDynamicColors
@Composable
private fun WidgetConfigScreenPreviewWithNav() {
    MyShatelMobileAppTheme {
        WidgetConfigContent(
            onSelectMode = {}
        )
    }
}