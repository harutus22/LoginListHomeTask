package com.example.loginlisthometask.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.loginlisthometask.R
import com.example.loginlisthometask.composables.OutlinedLoginTextField
import com.example.loginlisthometask.viewmodels.event.*
import com.example.loginlisthometask.viewmodels.states.LoginUiState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEvent: (event: LoginEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.login),
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(Modifier.height(24.dp))
        OutlinedLoginTextField(
            uiState.username,
            R.string.username,
            false,
        ) {
            onEvent(UsernameChanged(it))
        }
        Spacer(Modifier.height(24.dp))
        OutlinedLoginTextField(
            uiState.password,
            R.string.password,
            true,
        ) {
            onEvent(PasswordChanged(it))
        }

        uiState.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                onEvent(LoginClicked)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !uiState.isLoading
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(stringResource(R.string.login))
            }
        }
    }
}


