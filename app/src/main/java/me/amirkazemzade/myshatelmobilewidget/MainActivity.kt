package me.amirkazemzade.myshatelmobilewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.amirkazemzade.myshatelmobilewidget.ui.AuthStateViewModel
import me.amirkazemzade.myshatelmobilewidget.ui.navigation.NavGraph
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelMobileAppTheme
import me.amirkazemzade.myshatelmobilewidget.widgets.remained.setRemainedWidgetPreview

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authStateViewModel: AuthStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyShatelMobileAppTheme {
                NavGraph(authStateViewModel = authStateViewModel)
            }
        }

        lifecycleScope.launch {
            setRemainedWidgetPreview(this@MainActivity)
        }
    }
}
