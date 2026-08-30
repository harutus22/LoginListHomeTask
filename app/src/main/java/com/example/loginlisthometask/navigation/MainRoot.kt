package com.example.loginlisthometask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.loginlisthometask.screens.MainScreen
import com.example.loginlisthometask.viewmodels.MainScreenViewModel
import com.example.loginlisthometask.viewmodels.effects.LogoutEffect

@Composable
fun MainRoot(
    viewModel: MainScreenViewModel,
    backstack: NavBackStack<NavKey>,
    username: String
) {
    val uiState by viewModel.vehicleState.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                LogoutEffect -> {
                    if (backstack.size == 1)
                        backstack[0] = Route.LoginRoute
                    else
                        backstack.removeLastOrNull()
                }
            }
        }
    }
    MainScreen(
        uiState = uiState,
        userName = username,
        onEvent = viewModel::onEvent
    )
}