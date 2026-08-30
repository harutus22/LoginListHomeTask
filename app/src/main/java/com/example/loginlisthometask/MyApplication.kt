package com.example.loginlisthometask

import android.app.Application
import com.example.loginlisthometask.database.UserPreferences

class MyApplication: Application() {
    val userPreferences by lazy {
        UserPreferences(this)
    }
}