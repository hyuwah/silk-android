package dev.hyuwah.silk.feature.account.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@Composable
fun AccountDataSwitcher(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.account_data_switcher_label),
            style = SilkTextStyle.formInputLabel
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledIconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_person_pin),
                    contentDescription = null
                )
            }

            Column {
                Text(
                    text = stringResource(R.string.account_personal_data_title),
                    style = SilkTextStyle.cardTitle.copy(fontSize = 14.sp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.account_personal_data_desc),
                    style = SilkTextStyle.tertiaryInfo.copy(fontSize = 10.sp)
                )
            }

            FilledIconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                enabled = false
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountDataSwitcherPreview() {
    SILKTheme {
        AccountDataSwitcher()
    }
}