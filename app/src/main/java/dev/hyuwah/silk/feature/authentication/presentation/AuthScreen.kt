package dev.hyuwah.silk.feature.authentication.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.hyuwah.silk.navigation.Screens
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AuthScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = hiltViewModel()
    LaunchedEffect(key1 = viewModel) {
        viewModel.event.collectLatest {
            when (it) {
                is AuthEvent.LoginFailed -> {
                    Toast.makeText(context, it.result.exception?.message.orEmpty(), Toast.LENGTH_SHORT).show()
                }
                is AuthEvent.RegisterFailed -> {
                    Toast.makeText(context, it.result.exception?.message.orEmpty(), Toast.LENGTH_SHORT).show()
                }
                AuthEvent.LoginSuccess,
                AuthEvent.RegisterSuccess -> {
                    navController.navigate(route = Screens.Home.route) {
                        popUpTo(Screens.Auth.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
    Scaffold {
        Column(
            modifier = Modifier.padding(it),
        ) {
            AuthContent(
                state = viewModel.state,
                onLogin = viewModel::login,
                onRegister = viewModel::register,
            )
            if (viewModel.state.isLoading) {
                Dialog(onDismissRequest = {}) {
                    Card(colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}