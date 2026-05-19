package me.amirkazemzade.netwidget.ui.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import me.amirkazemzade.netwidget.domain.models.RequestStatus
import me.amirkazemzade.netwidget.ui.dashboard.components.DashboardView

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val logoutState by dashboardViewModel.logoutState.collectAsState()
    val remainedState by dashboardViewModel.remainedState.collectAsState()

    LaunchedEffect(Unit) {
        dashboardViewModel.scheduleWidgetUpdates(context)
    }

    DashboardView(
        remainedState = remainedState,
        isLoggingOut = logoutState is RequestStatus.Loading,
        onLogout = { dashboardViewModel.logout(context) },
        onRefreshRemained = dashboardViewModel::refreshRemained
    )
}