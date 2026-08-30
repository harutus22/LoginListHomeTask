package com.example.loginlisthometask.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun OutlinedLoginTextField(
    value: String,
    label: Int,
    isPassword: Boolean,
    valueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            valueChanged(it)
        },
        label = {
            Text(stringResource(label))
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = if (isPassword)
            PasswordVisualTransformation()
        else
            VisualTransformation.None
    )
}