package dev.hyuwah.silk.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.tab.SilkTab
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@Composable
fun HomeServiceTypeTab(
    modifier: Modifier = Modifier,
) {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Satuan", "Paket Pemeriksaan")
    Column(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Color.White.copy(0.8f),
                        Color.Transparent
                    )
                )
            )
            .then(modifier)
    ) {
        Text(
            text = stringResource(R.string.home_service_package_section_label),
            style = SilkTextStyle.formInputLabel
        )
        Spacer(Modifier.height(16.dp))
        SilkTab(
            selectedTabIndex = state,
            tabLabels = titles,
            onTabSelected = { state = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeServiceTypeTabPreview() {
    SILKTheme {
        HomeServiceTypeTab()
    }
}