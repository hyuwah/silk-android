package dev.hyuwah.silk.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
        composable(Screens.Profile.route) {
            Scaffold {
                Box(modifier = Modifier.padding(it)) {
                    Text(text = "Profile Placeholder")
                }
            }
        }
    }
}