package dev.hyuwah.silk.feature.account.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.theme.DarkBlue2
import dev.hyuwah.silk.ui.theme.PaleDarkBlue
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "Angga Praja",
                    style = SilkTextStyle.cardTitle.copy(color = MaterialTheme.colorScheme.onPrimary)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Membership BBLK",
                    style = SilkTextStyle.tertiaryInfo.copy(fontSize = 14.sp, color = PaleDarkBlue)
                )
            }
        }
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = DarkBlue2,
        ) {
            Text(
                text = stringResource(R.string.profile_card_notice),
                style = SilkTextStyle.body.copy(color = MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    SILKTheme {
        ProfileCard()
    }
}