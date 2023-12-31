package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.hyuwah.silk.feature.home.presentation.component.HomeDrawerMenu
import dev.hyuwah.silk.navigation.Screens
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.primary.copy(0.8f),
        drawerContent = {
            HomeDrawerMenu(
                navController = navController,
                userData = viewModel.state.userData,
                onNavigate = {
                    scope.launch {
                        drawerState.close()
                    }
                },
                onLogout = {
                    viewModel.clearPrefs()
                    navController.navigate(Screens.Auth.route) {
                        popUpTo(Screens.Home.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    ) {
        HomeContent(
            state = viewModel.state,
            onNavMenuClicked = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }
}