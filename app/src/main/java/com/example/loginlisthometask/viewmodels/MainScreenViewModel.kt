package com.example.loginlisthometask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginlisthometask.database.UserPreferences
import com.example.loginlisthometask.database.VehiclesDatabase
import com.example.loginlisthometask.viewmodels.event.LoadVehicles
import com.example.loginlisthometask.viewmodels.event.Logout
import com.example.loginlisthometask.viewmodels.event.MainScreenEvent
import com.example.loginlisthometask.viewmodels.effects.LogoutEffect
import com.example.loginlisthometask.viewmodels.effects.MainScreenEffect
import com.example.loginlisthometask.viewmodels.states.VehicleState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainScreenViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {
    private var _vehiclesState = MutableStateFlow(VehicleState())
    val vehicleState = _vehiclesState.asStateFlow()

    private val _effects = MutableSharedFlow<MainScreenEffect>()
    val effects = _effects.asSharedFlow()

    fun onEvent(action: MainScreenEvent) {
        when(action) {
            is Logout -> logout()
            is LoadVehicles -> getVehicles()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            userPreferences.logout()
            _effects.emit(LogoutEffect)
        }
    }

    private fun getVehicles() {
        if (_vehiclesState.value.list.isNotEmpty() ||
            _vehiclesState.value.isLoading
        ) {
            return
        }
        viewModelScope.launch {
            _vehiclesState.update { it.copy(isLoading = true) }
            delay(3.seconds)
            _vehiclesState.update {
                it.copy(
                    isLoading = false,
                    list = VehiclesDatabase.vehicles
                )
            }
        }
    }
}