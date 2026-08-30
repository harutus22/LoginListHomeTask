package com.example.loginlisthometask.viewmodels.states

import com.example.loginlisthometask.data.Vehicle

data class VehicleState(
    val list:List<Vehicle> = emptyList(),
    val isLoading: Boolean = false,
)
