package dev.hyuwah.silk.feature.account.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.hyuwah.silk.common.data.local.AppPreferences
import dev.hyuwah.silk.common.domain.model.UserData
import javax.inject.Inject

data class AccountState(
    val userData: UserData? = null,
)

@HiltViewModel
class AccountViewModel @Inject constructor(
    appPreferences: AppPreferences
): ViewModel() {

    var state by mutableStateOf(AccountState())

    init {
        state = state.copy(
            userData = appPreferences.userData
        )
    }

}