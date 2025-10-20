package me.amirkazemzade.myshatelmobilewidget.ui.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.ui.dashboard.components.DashboardView

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {
    val logoutState by dashboardViewModel.logoutState.collectAsState()
    val remainedState by dashboardViewModel.remainedState.collectAsState()

    DashboardView(
        remainedState = remainedState,
        isLoggingOut = logoutState is RequestStatus.Loading,
        onLogout = dashboardViewModel::logout,
        onRefreshRemained = dashboardViewModel::refreshRemained
    )
}