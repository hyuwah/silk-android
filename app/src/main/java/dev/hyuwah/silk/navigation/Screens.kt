package dev.hyuwah.silk.navigation

sealed class Screens(val route: String) {
    data object Auth: Screens("auth")
    data object Home: Screens("home")
    data object Account: Screens("account")
}
