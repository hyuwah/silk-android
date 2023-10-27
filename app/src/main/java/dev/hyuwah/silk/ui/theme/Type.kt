package dev.hyuwah.silk.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.hyuwah.silk.R

val gilroyFontFamily = FontFamily(
    Font(R.font.gilroy_light, FontWeight.Light),
    Font(R.font.gilroy_regular, FontWeight.Normal),
    Font(R.font.gilroy_semibold, FontWeight.SemiBold),
    Font(R.font.gilroy_bold, FontWeight.Bold),
    Font(R.font.gilroy_heavy, FontWeight.ExtraBold),
)
val proximaNovaFontFamily = FontFamily(
    Font(R.font.proximanova_light, FontWeight.Light),
    Font(R.font.proximanova_regular, FontWeight.Normal),
    Font(R.font.proximanova_semibold, FontWeight.SemiBold),
    Font(R.font.proximanova_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

object SilkTextStyle {
    val mainTitle = TextStyle(
        fontSize = 26.sp,
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.SemiBold,
        color = DarkBlue,
    )
    val body = TextStyle(
        fontSize = 12.sp,
        fontFamily = proximaNovaFontFamily,
        color = Grey,
    )
    val tertiaryInfo = TextStyle(
        fontSize = 12.sp,
        fontFamily = proximaNovaFontFamily,
        fontWeight = FontWeight.SemiBold,
        color = LightGrey,
    )
    val formInputLabel = TextStyle(
        fontSize = 16.sp,
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.SemiBold,
        color = DarkBlue,
    )
}