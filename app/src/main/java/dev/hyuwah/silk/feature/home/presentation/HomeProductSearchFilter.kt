package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.form.SilkTextField
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@Composable
fun HomeProductSearchFilter(
    modifier: Modifier = Modifier,
) {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_tune), contentDescription = null)
            }
            SilkTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(text = "Search", style = SilkTextStyle.body.copy(fontSize = 16.sp))
                },
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                    }
                },
                roundedRadius = 32.dp,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeProductSearchFilterPreview() {
    SILKTheme {
        HomeProductSearchFilter()
    }
}