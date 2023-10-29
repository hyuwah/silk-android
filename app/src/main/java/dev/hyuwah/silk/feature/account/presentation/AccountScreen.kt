package dev.hyuwah.silk.feature.account.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AccountScreen(navController: NavController) {
    val viewModel: AccountViewModel = hiltViewModel()
    AccountContent(
        state = viewModel.state,
        onNavigationBack = {
            navController.navigateUp()
        }
    )
}