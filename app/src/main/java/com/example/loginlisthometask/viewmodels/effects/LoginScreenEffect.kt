package com.example.loginlisthometask.viewmodels.effects

sealed interface LoginEffect

data class LoggedIn(val username: String): LoginEffect