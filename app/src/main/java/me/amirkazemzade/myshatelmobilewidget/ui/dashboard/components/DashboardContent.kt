package me.amirkazemzade.myshatelmobilewidget.ui.dashboard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import me.amirkazemzade.myshatelmobilewidget.R
import me.amirkazemzade.myshatelmobilewidget.domain.models.Remained
import me.amirkazemzade.myshatelmobilewidget.domain.models.Traffic
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelDimensions
import me.amirkazemzade.myshatelmobilewidget.ui.theme.MyShatelMobileWidgetTheme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DashboardContent(
    remained: Remained?,
    onReloadClicked: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = MyShatelDimensions.medium, horizontal = MyShatelDimensions.large)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = MyShatelDimensions.medium)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.width(40.dp))
                    Text(
                        "Data",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .whatIfMap(remained == null) {
                                it.shimmer()
                            }
                    )
                    IconButton(
                        enabled = remained != null,
                        onClick = onReloadClicked,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.rounded_refresh_24),
                            contentDescription = "Reload Remained"
                        )
                    }

                }
                Box(modifier = Modifier.height(MyShatelDimensions.medium))
                DataCircularProgress(
                    remained = remained,
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun DashboardContentPreview() {
    MyShatelMobileWidgetTheme {
        Surface {
            DashboardContent(remainedMockValue)
        }
    }
}

@PreviewLightDark
@Composable
private fun DashboardContentWithMBPreview() {
    MyShatelMobileWidgetTheme {
        Surface {
            DashboardContent(remainedMockLessThan1GValue)
        }
    }
}

@PreviewLightDark
@Composable
private fun DashboardContentLoadingPreview() {
    MyShatelMobileWidgetTheme {
        Surface {
            DashboardContent(null)
        }
    }
}

val remainedMockValue: Remained
    get() {
        val amount = 15460L
        val totalAmount = 20000L
        return Remained(Traffic(amount), percentage = amount * 1f / totalAmount)
    }

val remainedMockLessThan1GValue: Remained
    get() {
        val amount = 999L
        val totalAmount = 20000L
        return Remained(Traffic(amount), percentage = amount * 1f / totalAmount)
    }
