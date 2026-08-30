package com.example.loginlisthometask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginlisthometask.database.UserPreferences
import com.example.loginlisthometask.viewmodels.effects.LoginEffect
import com.example.loginlisthometask.viewmodels.effects.LoggedIn
import com.example.loginlisthometask.viewmodels.event.LoginClicked
import com.example.loginlisthometask.viewmodels.event.LoginEvent
import com.example.loginlisthometask.viewmodels.event.PasswordChanged
import com.example.loginlisthometask.viewmodels.event.UsernameChanged
import com.example.loginlisthometask.viewmodels.states.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class LoginViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val credentialsLength = 4

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<LoginEffect>()
    val effects = _effects.asSharedFlow()

    fun onEvent(fieldState: LoginEvent) {
        when (fieldState) {
            is UsernameChanged -> _uiState.update {
                it.copy(username = fieldState.text)
            }
            is PasswordChanged -> _uiState.update {
                it.copy(password = fieldState.text)
            }
            is LoginClicked -> login()
        }
    }

    private fun login() {
        val state = _uiState.value

        if (state.username.length < credentialsLength || state.password.length < credentialsLength) {
            _uiState.update {
                it.copy(error = "Username and password should be at least 4 characters long")
            }
            return
        }
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            delay(1.seconds)

            userPreferences.setLoggedIn(state.username)

            launch {
                delay(1.seconds)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        username = "",
                        password = "",
                    )
                }
            }

            _effects.emit(
                LoggedIn(state.username)
            )
        }
    }
}