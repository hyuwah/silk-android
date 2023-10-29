package dev.hyuwah.silk.ui.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.ui.theme.Cyan
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@Composable
fun SilkTabItem(title: String, selected: Boolean, onClick: () -> Unit) {
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
fun SilkTabItemSelectedPreview() {
    SILKTheme {
        SilkTabItem(title = "Item 1", selected = true) {}
    }
}

@Preview(showBackground = true)
@Composable
fun SilkTabItemUnselectedPreview() {
    SILKTheme {
        SilkTabItem(title = "Item 1", selected = false) {}
    }
}