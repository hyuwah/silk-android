package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.common.utils.toRupiahString
import dev.hyuwah.silk.feature.home.data.local.DummyLocalDataSource
import dev.hyuwah.silk.feature.home.domain.model.ServicePackage
import dev.hyuwah.silk.ui.modifier.plainClickable
import dev.hyuwah.silk.ui.theme.Grey
import dev.hyuwah.silk.ui.theme.Orange
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@Composable
fun ServicePackageCard(
    data: ServicePackage,
    onClicked: (data: ServicePackage) -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedPrice by remember {
        mutableStateOf(data.price.toRupiahString())
    }
    Surface(
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        modifier = modifier
            .padding(vertical = 8.dp)
            .plainClickable { onClicked(data) }
    ) {
        Row(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = data.name,
                    style = SilkTextStyle.cardTitle,
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = formattedPrice,
                    style = SilkTextStyle.cardTitle.copy(Orange),
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_business), contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = data.place,
                        style = SilkTextStyle.cardTitle.copy(color = Grey),
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = data.address,
                        style = SilkTextStyle.cardTitle.copy(color = Color.LightGray),
                    )
                }
            }
            Image(
                painter = painterResource(id = data.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .aspectRatio(3f / 5f)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicePackageCardPreview() {
    val data by remember {
        mutableStateOf(DummyLocalDataSource.getServicePackages().first())
    }
    SILKTheme {
        ServicePackageCard(data = data, onClicked = {})
    }
}