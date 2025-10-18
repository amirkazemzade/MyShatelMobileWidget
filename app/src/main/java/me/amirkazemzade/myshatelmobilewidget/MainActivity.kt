package me.amirkazemzade.myshatelmobilewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.amirkazemzade.myshatelmobilewidget.ui.AuthStateViewModel
import me.amirkazemzade.myshatelmobilewidget.ui.navigation.NavGraph
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelMobileWidgetTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authStateViewModel: AuthStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyShatelMobileWidgetTheme {
                NavGraph(authStateViewModel = authStateViewModel)
            }
        }
    }
}
