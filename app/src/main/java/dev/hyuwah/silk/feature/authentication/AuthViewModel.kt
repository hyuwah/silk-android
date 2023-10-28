package dev.hyuwah.silk.feature.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hyuwah.silk.common.domain.model.CallResult
import dev.hyuwah.silk.common.domain.model.LoginRegisterRequest
import dev.hyuwah.silk.common.domain.repository.AuthRepository
import dev.hyuwah.silk.feature.authentication.domain.model.RegistrationData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthState(
    val loginFormState: LoginFormState = LoginFormState(),
    val registerFormState: RegisterFormState = RegisterFormState(),
    val isLoading: Boolean = false
)

sealed interface AuthEvent {
    data object LoginSuccess: AuthEvent
    data object RegisterSuccess: AuthEvent
    data class LoginFailed(val result: CallResult.Failed): AuthEvent
    data class RegisterFailed(val result: CallResult.Failed): AuthEvent
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    var state by mutableStateOf(AuthState(LoginFormState(), RegisterFormState()))
    val event = MutableSharedFlow<AuthEvent>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, loginFormState = LoginFormState(email, password))
            val result = authRepository.login(LoginRegisterRequest(
                email = email,
                password = password
            ))
            when (result) {
                is CallResult.Failed -> {
                    state = state.copy(isLoading = false)
                    event.emit(AuthEvent.LoginFailed(result))
                }
                is CallResult.Success -> {
                    state = state.copy(isLoading = false)
                    event.emit(AuthEvent.LoginSuccess)
                }
            }
        }
    }

    fun register(registrationData: RegistrationData) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, registerFormState = RegisterFormState(registrationData))
            val result = authRepository.register(LoginRegisterRequest(
                email = registrationData.email,
                password = registrationData.password
            ))
            when (result) {
                is CallResult.Failed -> {
                    state = state.copy(isLoading = false)
                    event.emit(AuthEvent.RegisterFailed(result))
                }
                is CallResult.Success -> {
                    state = state.copy(isLoading = false)
                    event.emit(AuthEvent.RegisterSuccess)
                }
            }
        }
    }

}