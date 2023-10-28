package dev.hyuwah.silk.feature.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.feature.authentication.domain.model.RegistrationData
import dev.hyuwah.silk.ui.theme.PaleDarkBlue
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

enum class FormType {
    Login, Register
}

@Composable
fun AuthContent(
    state: AuthState,
    modifier: Modifier = Modifier,
    onLogin: (email: String, password: String) -> Unit = {_,_ -> },
    onRegister: (registrationData: RegistrationData) -> Unit = {}
) {
    var formType by remember {
        mutableStateOf(FormType.Login)
    }
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.End
    ) {
        val authTitle = buildAnnotatedString {
            append("Hai, ")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Selamat Datang")
            }
        }
        Spacer(Modifier.height(20.dp))
        Text(
            text = authTitle,
            style = SilkTextStyle.mainTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Text(
            text = "Silahkan login untuk melanjutkan",
            style = SilkTextStyle.body.copy(fontWeight = FontWeight.SemiBold, color = PaleDarkBlue),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.illustration_login),
            contentDescription = null,
            modifier = Modifier.height(240.dp)
        )

        // Login Form or Register Form
        when (formType) {
            FormType.Login -> {
                LoginForm(
                    state = state.loginFormState,
                    onEvent = { event ->
                              when (event) {
                                  LoginFormEvent.ForgotPasswordClicked -> {}
                                  is LoginFormEvent.LoginClicked -> { onLogin(event.email, event.password) }
                                  LoginFormEvent.SwitchToRegister -> { formType = FormType.Register }
                              }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            FormType.Register -> {
                RegisterForm(
                    state = state.registerFormState,
                    onEvent = { event ->
                        when (event) {
                            is RegisterFormEvent.RegisterClicked -> { onRegister(event.registrationData) }
                            RegisterFormEvent.SwitchToLogin -> { formType = FormType.Login }
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        Text(
            text = "© SILK. All rights reserved.",
            style = SilkTextStyle.tertiaryInfo,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthContentPreview() {
    SILKTheme {
        AuthContent(
            state = AuthState(),
        )
    }
}