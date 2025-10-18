package me.amirkazemzade.myshatelmobilewidget.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import me.amirkazemzade.myshatelmobilewidget.domain.models.Remained
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
import me.amirkazemzade.myshatelmobilewidget.ui.login.components.FieldButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {
    val logoutState by dashboardViewModel.logoutState.collectAsState()
    val remainedState by dashboardViewModel.remainedState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Dashboard") }) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (remainedState) {
                RequestStatus.Loading, Status.Idle -> ContainedLoadingIndicator()
                is RequestStatus.Error -> Text((remainedState as RequestStatus.Error).message)
                is RequestStatus.Success<Remained> -> Text("Remained: ${(remainedState as RequestStatus.Success<Remained>).data.traffic.toGB()}")
            }
            FieldButton(
                text = "Logout",
                isLoading = logoutState is RequestStatus.Loading,
                onClick = dashboardViewModel::logout
            )
        }
    }
}
