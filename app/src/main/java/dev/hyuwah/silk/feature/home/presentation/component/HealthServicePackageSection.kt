package dev.hyuwah.silk.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.gigamole.composeshadowsplus.common.ShadowsPlusType
import com.gigamole.composeshadowsplus.common.shadowsPlus
import com.gigamole.composeshadowsplus.softlayer.SoftLayerShadowContainer
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.theme.Cyan
import dev.hyuwah.silk.ui.theme.LightGrey
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
        Text(text = stringResource(R.string.home_service_package_section_label), style = SilkTextStyle.formInputLabel)
        Spacer(Modifier.height(16.dp))
        SoftLayerShadowContainer {
            TabRow(
                selectedTabIndex = state,
                indicator = {},
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .shadowsPlus(
                        type = ShadowsPlusType.SoftLayer,
                        radius = 2.dp,
                        spread = 1.dp,
                        color = LightGrey.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(24.dp),
                        offset = DpOffset(0.dp, 2.dp)
                    )
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(24.dp))

            ) {
                titles.forEachIndexed { index, title ->
                    FancyTab(
                        title = title,
                        onClick = { state = index },
                        selected = (index == state)
                    )
                }
            }
        }
    }
}

@Composable
fun FancyTab(title: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) Cyan else Color.Transparent
    Tab(selected, onClick) {
        Column(
            Modifier
                .padding(4.dp)
                .wrapContentHeight()
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(backgroundColor)
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = title,
                style = SilkTextStyle.tabLabel.copy(
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeServiceTypeTabPreview() {
    SILKTheme {
        HomeServiceTypeTab()
    }
}