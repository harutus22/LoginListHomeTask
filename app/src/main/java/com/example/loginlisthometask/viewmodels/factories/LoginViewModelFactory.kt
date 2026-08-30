package com.example.loginlisthometask.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginlisthometask.database.UserPreferences
import com.example.loginlisthometask.viewmodels.LoginViewModel

class LoginViewModelFactory(
    private val userPreferences: UserPreferences,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userPreferences) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}