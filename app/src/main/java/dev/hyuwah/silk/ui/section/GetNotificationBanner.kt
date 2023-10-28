package dev.hyuwah.silk.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.silk.ui.theme.DarkBlue
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.gilroyFontFamily
import dev.hyuwah.silk.ui.theme.proximaNovaFontFamily

@Composable
fun GetNotificationBanner(
    onGetNotificationClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clickable {
            onGetNotificationClicked()
        }.background(DarkBlue).padding(horizontal = 16.dp, vertical = 28.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ingin mendapat update dari kami?",
                fontSize = 16.sp,
                fontFamily = gilroyFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.weight(2.5f)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Dapatkan notifikasi",
                fontSize = 14.sp,
                fontFamily = proximaNovaFontFamily,
                color = Color.White,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun GetNotificationBannerPreview() {
    SILKTheme {
        GetNotificationBanner(onGetNotificationClicked = {})
    }
}