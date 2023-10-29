package dev.hyuwah.silk.feature.home.presentation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import dev.hyuwah.silk.feature.home.presentation.component.HomeDrawerMenu
import dev.hyuwah.silk.ui.theme.DarkBlue
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = DarkBlue.copy(0.8f),
        drawerContent = {
            HomeDrawerMenu(
                navController = navController,
                onNavigate = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        HomeContent(
            onNavMenuClicked = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }
}