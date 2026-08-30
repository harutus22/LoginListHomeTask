package com.example.loginlisthometask.viewmodels.event

sealed class MainScreenEvent

data object LoadVehicles: MainScreenEvent()
data object Logout: MainScreenEvent()
