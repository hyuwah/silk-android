package dev.hyuwah.silk.feature.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.button.CTAButton
import dev.hyuwah.silk.ui.form.SilkPasswordTextField
import dev.hyuwah.silk.ui.form.SilkTextField
import dev.hyuwah.silk.ui.theme.DarkBlue
import dev.hyuwah.silk.ui.theme.LightGrey
import dev.hyuwah.silk.ui.theme.PaleDarkBlue
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

data class LoginFormState(
    val email: String = "",
    val password: String = "",
)

sealed interface LoginFormEvent {
    object SwitchToRegister : LoginFormEvent
    object ForgotPasswordClicked : LoginFormEvent
    data class LoginClicked(
        val email: String,
        val password: String,
    ) : LoginFormEvent
}

@Composable
fun LoginForm(
    state: LoginFormState,
    onEvent: (event: LoginFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf(state.email) }
    var password by remember { mutableStateOf(state.password) }

    Column(
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.label_email), style = SilkTextStyle.formInputLabel)
        SilkTextField(
            value = email, onValueChange = { email = it },
            placeholder = {
                Text(text = stringResource(R.string.input_placeholder_email), style = SilkTextStyle.body)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
        )



        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.label_password), style = SilkTextStyle.formInputLabel)
            Text(text = stringResource(R.string.button_forgot_password),
                style = SilkTextStyle.body.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PaleDarkBlue
                ),
                modifier = Modifier
                    .focusProperties { canFocus = false }
                    .clickable {
                        onEvent(LoginFormEvent.ForgotPasswordClicked)
                    })
        }
        SilkPasswordTextField(
            value = password, onValueChange = { password = it },
            placeholder = {
                Text(text = stringResource(R.string.input_placeholder_password), style = SilkTextStyle.body)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))
        CTAButton(
            text = stringResource(R.string.button_login),
            enabled = email.isNotBlank(),
            onClick = {
                onEvent(LoginFormEvent.LoginClicked(email, password))
            },
            trailingIcon = Icons.Default.ArrowForward
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.auth_switch_to_register_label),
                style = SilkTextStyle.body.copy(color = LightGrey)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.auth_switch_to_register_cta),
                style = SilkTextStyle.body.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = DarkBlue
                ),
                modifier = Modifier.clickable {
                    onEvent(LoginFormEvent.SwitchToRegister)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    SILKTheme {
        LoginForm(
            state = LoginFormState(),
            onEvent = {}
        )
    }
}