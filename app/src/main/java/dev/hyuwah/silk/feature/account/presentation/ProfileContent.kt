package dev.hyuwah.silk.feature.account.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.feature.account.presentation.component.AccountDataSwitcher
import dev.hyuwah.silk.feature.account.presentation.component.PersonalDataForm
import dev.hyuwah.silk.feature.account.presentation.component.PersonalDataFormState
import dev.hyuwah.silk.feature.account.presentation.component.ProfileCard
import dev.hyuwah.silk.ui.theme.SILKTheme


@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    Surface(
        shadowElevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier,
    ) {
        Column {
            // Profile Card
            ProfileCard()
            Spacer(modifier = Modifier.height(20.dp))
            // Account Data Switcher
            AccountDataSwitcher(
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Personal Data
            PersonalDataForm(
                state = PersonalDataFormState(),
                onSaveClicked = {},
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileContentPreview() {
    SILKTheme {
        ProfileContent()
    }
}