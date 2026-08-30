package com.example.loginlisthometask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.loginlisthometask.screens.LoginScreen
import com.example.loginlisthometask.viewmodels.LoginViewModel
import com.example.loginlisthometask.viewmodels.effects.LoggedIn

@Composable
fun LoginRoot(
    viewModel: LoginViewModel,
    backstack: NavBackStack<NavKey>
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    //or should I add this logic to view model with backstack
    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is LoggedIn -> {
                    backstack.add(
                        Route.VehiclesListRoute(effect.username)
                    )
                }
            }
        }
    }
    LoginScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}