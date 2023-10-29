package dev.hyuwah.silk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.hyuwah.silk.feature.account.presentation.AccountScreen
import dev.hyuwah.silk.feature.authentication.presentation.AuthScreen
import dev.hyuwah.silk.feature.home.presentation.HomeScreen

@Composable
fun SilkNavGraph(
    navController: NavHostController, startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.Auth.route) {
            AuthScreen(navController = navController)
        }
        composable(Screens.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screens.Account.route) {
            AccountScreen(navController = navController)
        }
    }
}