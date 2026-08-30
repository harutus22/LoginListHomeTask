package com.example.loginlisthometask.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginlisthometask.database.UserPreferences
import com.example.loginlisthometask.viewmodels.MainScreenViewModel

class MainScreenViewModelFactory(
    private val userPreferences: UserPreferences,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainScreenViewModel(userPreferences) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}