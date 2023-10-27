package dev.hyuwah.silk.ui.form

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import dev.hyuwah.silk.R
import dev.hyuwah.silk.ui.theme.SilkTextStyle

@Composable
fun SilkPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit, modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false, textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    placeholderText: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val visibilityIconRes =
        if (isPasswordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
    val passwordTransformation =
        if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()

    SilkTextField(
        value = value, onValueChange = onValueChange,
        placeholder = placeholder,
        visualTransformation = passwordTransformation,
        supportingText = supportingText?.let {
            {
                Text(text = it, style = SilkTextStyle.body.copy(color = Color.Red))
            }
        },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
                modifier = Modifier.focusProperties { canFocus = false }) {
                Icon(painter = painterResource(id = visibilityIconRes), contentDescription = null)
            }
        },
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = keyboardActions,
        modifier = modifier,
    )
}