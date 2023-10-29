package dev.hyuwah.silk.ui.tab

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.gigamole.composeshadowsplus.common.ShadowsPlusType
import com.gigamole.composeshadowsplus.common.shadowsPlus
import com.gigamole.composeshadowsplus.softlayer.SoftLayerShadowContainer
import dev.hyuwah.silk.ui.theme.LightGrey
import dev.hyuwah.silk.ui.theme.SILKTheme

@Composable
fun SilkTab(
    selectedTabIndex: Int,
    tabLabels: List<String>,
    onTabSelected: (selectedTabIndex: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    SoftLayerShadowContainer {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = {},
            modifier = modifier
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
            tabLabels.forEachIndexed { index, title ->
                SilkTabItem(
                    title = title,
                    onClick = { onTabSelected(index) },
                    selected = (index == selectedTabIndex)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SilkTabPreview() {
    val tabLabels = listOf("Item 1", "Item 2", "Item 3")
    SILKTheme {
        SilkTab(selectedTabIndex = 0, tabLabels = tabLabels, onTabSelected = {})
    }
}