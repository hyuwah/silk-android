package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.common.utils.toRupiahString
import dev.hyuwah.silk.feature.home.data.local.DummyLocalDataSource
import dev.hyuwah.silk.feature.home.domain.model.Product
import dev.hyuwah.silk.ui.theme.LightGrey
import dev.hyuwah.silk.ui.theme.Orange
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle
import dev.hyuwah.silk.ui.theme.Yellow
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    data: Product,
    onClicked: (data: Product) -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedPrice by remember {
        mutableStateOf(data.price.toRupiahString())
    }
    val status = when (data.status) {
        Product.Status.Available -> stringResource(R.string.product_status_available)
        Product.Status.Unavailable -> stringResource(R.string.product_status_unavailable)
    }
    val statusColor = when (data.status) {
        Product.Status.Available -> Color.Green
        Product.Status.Unavailable -> Color.Red
    }

    Surface(
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClicked(data)
            }
    ) {
        Column(
            modifier = Modifier.width(160.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(id = data.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1f / 1f)
                        .size(160.dp),
                    contentScale = ContentScale.Fit,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Yellow)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = data.rating.roundToInt().toString(),
                        style = SilkTextStyle.cardTitle.copy(color = LightGrey),
                    )
                }
            }
            Column(
                modifier = Modifier.padding(12.dp),
            ) {
                Text(
                    text = data.name,
                    style = SilkTextStyle.cardTitle.copy(fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = formattedPrice,
                        style = SilkTextStyle.cardTitle.copy(Orange, fontSize = 14.sp),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(6.dp))
                    Badge(
                        containerColor = statusColor.copy(0.5f),
                        contentColor = statusColor,
                    ) {
                        Text(
                            text = status,
                            style = SilkTextStyle.cardTitle.copy(fontSize = 9.sp),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    val data by remember {
        mutableStateOf(DummyLocalDataSource.getProducts().first())
    }
    SILKTheme {
        ProductCard(data = data, onClicked = {})
    }
}