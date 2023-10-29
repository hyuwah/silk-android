package dev.hyuwah.silk.feature.account.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.button.CTAButton
import dev.hyuwah.silk.ui.form.SilkTextField
import dev.hyuwah.silk.ui.theme.SILKTheme
import dev.hyuwah.silk.ui.theme.SilkTextStyle

data class PersonalDataFormState(
    val firstName: String = "",
    val lastName: String = "",
    val nationalIdNumber: String = "",
    val email: String = "",
    val phoneNumber: String = "",
)

@Composable
fun PersonalDataForm(
    state: PersonalDataFormState,
    onSaveClicked: (state: PersonalDataFormState) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var firstName by remember { mutableStateOf(state.firstName) }
    var lastName by remember { mutableStateOf(state.lastName) }
    var nationalIdNumber by remember { mutableStateOf(state.nationalIdNumber) }
    var email by remember { mutableStateOf(state.email) }
    var phoneNumber by remember { mutableStateOf(state.phoneNumber) }

    val isFormValid by remember {
        derivedStateOf {
            firstName.isNotBlank()
                    && lastName.isNotBlank()
                    && nationalIdNumber.isNotBlank()
                    && email.isNotBlank()
                    && phoneNumber.isNotBlank()
        }
    }
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.label_first_name),
            style = SilkTextStyle.formInputLabel
        )
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

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.label_last_name),
            style = SilkTextStyle.formInputLabel
        )
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

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.label_email), style = SilkTextStyle.formInputLabel)
        SilkTextField(
            value = email, onValueChange = { email = it },
            placeholder = {
                Text(
                    text = stringResource(R.string.input_placeholder_email),
                    style = SilkTextStyle.body
                )
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
        Text(text = stringResource(R.string.label_phone), style = SilkTextStyle.formInputLabel)
        SilkTextField(
            value = phoneNumber, onValueChange = { phoneNumber = it },
            placeholder = {
                Text(
                    text = stringResource(R.string.input_placeholder_phone),
                    style = SilkTextStyle.body
                )
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
        Text(
            text = stringResource(R.string.label_national_id_num),
            style = SilkTextStyle.formInputLabel
        )
        SilkTextField(
            value = nationalIdNumber, onValueChange = { nationalIdNumber = it },
            placeholder = {
                Text(
                    text = stringResource(R.string.input_placeholder_national_id_num),
                    style = SilkTextStyle.body
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.account_personal_data_form_info),
                style = SilkTextStyle.body,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        CTAButton(
            text = stringResource(R.string.account_personal_data_save),
            enabled = isFormValid,
            onClick = {
                onSaveClicked(
                    PersonalDataFormState(firstName, lastName, nationalIdNumber, email, phoneNumber)
                )
            },
            trailingIconPainter = painterResource(id = R.drawable.ic_save)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDataFormPreview() {
    SILKTheme {
        PersonalDataForm(
            state = PersonalDataFormState(),
            onSaveClicked = {}
        )
    }
}