package me.amirkazemzade.myshatelmobilewidget.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.amirkazemzade.myshatelmobilewidget.domain.models.RequestStatus
import me.amirkazemzade.myshatelmobilewidget.domain.models.Status
import me.amirkazemzade.myshatelmobilewidget.ui.AuthStateViewModel
import me.amirkazemzade.myshatelmobilewidget.ui.dashboard.DashboardScreen
import me.amirkazemzade.myshatelmobilewidget.ui.login.LoginScreen
import me.amirkazemzade.myshatelmobilewidget.ui.splash.SplashScreen

@Composable
fun NavGraph(
    authStateViewModel: AuthStateViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val authState by authStateViewModel.authState.collectAsStateWithLifecycle()

    LaunchedEffect(authState) {
        if (authState is RequestStatus.Loading || authState is Status.Idle) return@LaunchedEffect
        val targetRoute =
            if (authState is RequestStatus.Success) Screen.Dashboard.route else Screen.Login.route
        navController.navigate(targetRoute) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen()
        }
        composable(Screen.Login.route) {
            LoginScreen()
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
    }
}
