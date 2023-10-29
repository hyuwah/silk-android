package dev.hyuwah.silk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.hyuwah.silk.common.data.local.AppPreferences
import dev.hyuwah.silk.navigation.Screens
import dev.hyuwah.silk.navigation.SilkNavGraph
import dev.hyuwah.silk.ui.theme.SILKTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var appPreferences: AppPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isAuthenticated = appPreferences.token.isNotBlank()
        setContent {
            val navController = rememberNavController()
            SILKTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SilkNavGraph(
                        navController = navController,
                        startDestination = if (isAuthenticated) Screens.Home.route else Screens.Auth.route
                    )
                }
            }
        }
    }
}