package com.example.loginlisthometask.viewmodels.event

sealed interface LoginEvent

data class UsernameChanged(val text: String): LoginEvent
data class PasswordChanged(val text: String): LoginEvent
data object LoginClicked: LoginEvent
