package dev.hyuwah.silk.feature.account.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AccountScreen(navController: NavController) {
    AccountContent(
        onNavigationBack = {
            navController.navigateUp()
        }
    )
}