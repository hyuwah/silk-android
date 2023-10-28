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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.ui.button.CTAButton
import dev.hyuwah.silk.ui.form.SilkPasswordTextField
import dev.hyuwah.silk.ui.form.SilkTextField
import dev.hyuwah.silk.ui.theme.DarkBlue
import dev.hyuwah.silk.ui.theme.LightGrey
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

data class RegisterFormState(
    val firstName: String = "",
    val lastName: String = "",
    val nationalIdNumber: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface RegisterFormEvent {
    object SwitchToLogin : RegisterFormEvent
    data class RegisterClicked(
        val firstName: String,
        val lastName: String,
        val nationalIdNumber: String,
        val email: String,
        val phoneNumber: String,
        val password: String
    ) : RegisterFormEvent
}

@Composable
fun RegisterForm(
    state: RegisterFormState,
    onEvent: (event: RegisterFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var firstName by remember { mutableStateOf(state.firstName) }
    var lastName by remember { mutableStateOf(state.lastName) }
    var nationalIdNumber by remember { mutableStateOf(state.nationalIdNumber) }
    var email by remember { mutableStateOf(state.email) }
    var phoneNumber by remember { mutableStateOf(state.phoneNumber) }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    val passwordNotSame by remember {
        derivedStateOf {
            password != passwordConfirmation
        }
    }
    val isFormValid by remember {
        derivedStateOf {
            firstName.isNotBlank()
                    && lastName.isNotBlank()
                    && nationalIdNumber.isNotBlank()
                    && email.isNotBlank()
                    && phoneNumber.isNotBlank()
                    && password.isNotBlank()
                    && passwordConfirmation == password
        }
    }

    Column(
        modifier = modifier
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Nama Depan", style = SilkTextStyle.formInputLabel)
                SilkTextField(
                    value = firstName, onValueChange = { firstName = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Nama Belakang", style = SilkTextStyle.formInputLabel)
                SilkTextField(
                    value = lastName, onValueChange = { lastName = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "No. KTP", style = SilkTextStyle.formInputLabel)
        SilkTextField(
            value = nationalIdNumber, onValueChange = { nationalIdNumber = it },
            placeholder = {
                Text(text = "Masukkan No. KTP anda", style = SilkTextStyle.body)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Email", style = SilkTextStyle.formInputLabel)
        SilkTextField(
            value = email, onValueChange = { email = it },
            placeholder = {
                Text(text = "Masukkan email anda", style = SilkTextStyle.body)
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
        Text(text = "No. Telpon", style = SilkTextStyle.formInputLabel)
        SilkTextField(
            value = phoneNumber, onValueChange = { phoneNumber = it },
            placeholder = {
                Text(text = "Masukkan No. Telpon anda", style = SilkTextStyle.body)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone
            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Password", style = SilkTextStyle.formInputLabel)
        SilkPasswordTextField(
            value = password, onValueChange = { password = it },
            placeholder = {
                Text(text = "Masukkan password anda", style = SilkTextStyle.body)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Konfirmasi Password", style = SilkTextStyle.formInputLabel)
        SilkPasswordTextField(
            value = passwordConfirmation, onValueChange = { passwordConfirmation = it },
            placeholder = {
                Text(text = "Konfirmasi password anda", style = SilkTextStyle.body)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
            },
            supportingText = if (passwordNotSame) "Konfirmasi password tidak sama" else null,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))
        CTAButton(text = "Register", enabled = isFormValid || state.isLoading, onClick = {
            onEvent(
                RegisterFormEvent.RegisterClicked(
                    firstName, lastName, nationalIdNumber, email, phoneNumber, password
                )
            )
        }, trailingIcon = Icons.Default.ArrowForward)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sudah punya akun?",
                style = SilkTextStyle.body.copy(color = LightGrey)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Login sekarang",
                style = SilkTextStyle.body.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = DarkBlue
                ),
                modifier = Modifier.clickable {
                    onEvent(RegisterFormEvent.SwitchToLogin)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterFormPreview() {
    SILKTheme {
        RegisterForm(
            state = RegisterFormState(),
            onEvent = {}
        )
    }
}