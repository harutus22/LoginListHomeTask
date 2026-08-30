package com.example.loginlisthometask.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey{
    @Serializable
    data class VehiclesListRoute(val userName: String): Route, NavKey
    @Serializable
    data object LoginRoute: Route, NavKey
}