package dev.hyuwah.silk.ui.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.gilroyFontFamily

@Composable
fun SilkOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 14.sp,
    onClick: () -> Unit,
    enabled: Boolean = true,
    trailingIcon: ImageVector? = null,
    shape: Shape = RoundedCornerShape(8.dp),
) {
    OutlinedButton(
        onClick = { onClick() },
        shape = shape,
        enabled = enabled,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                Text(text = text, fontSize = fontSize, fontFamily = gilroyFontFamily, fontWeight = FontWeight.SemiBold)
            }
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SilkTextButtonPreview() {
    SILKTheme {
        SilkOutlinedButton(
            text = "Text Button",
            onClick = {},
            trailingIcon = Icons.Default.ArrowForward
        )
    }
}