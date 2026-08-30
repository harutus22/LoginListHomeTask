package com.example.loginlisthometask.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.loginlisthometask.MyApplication
import com.example.loginlisthometask.database.UserPreferences
import com.example.loginlisthometask.viewmodels.LoginViewModel
import com.example.loginlisthometask.viewmodels.MainScreenViewModel
import com.example.loginlisthometask.viewmodels.factories.*
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Composable
fun InitialisationScreen(
    modifier: Modifier = Modifier
) {
    val application = LocalContext.current.applicationContext as MyApplication
    val userPreferences = application.userPreferences

    val isLoggedIn by userPreferences.isLoggedIn.collectAsStateWithLifecycle(
        initialValue = null
    )
    val username by userPreferences.username.collectAsStateWithLifecycle(
        initialValue = null
    )

    if (isLoggedIn == null || (isLoggedIn == true && username == null)) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        NavigationRoute(
            modifier,
            isLoggedIn,
            username,
            userPreferences
        )
    }
}

@Composable
fun NavigationRoute(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean?,
    username: String?,
    userPreferences: UserPreferences
) {
    val initialRoute = if (isLoggedIn == true) {
        Route.VehiclesListRoute(username ?: "")
    } else {
        Route.LoginRoute
    }

    val backstack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.LoginRoute::class)
                    subclass(Route.VehiclesListRoute::class)
                }
            }
        },
        initialRoute
    )

    NavDisplay(
        modifier = modifier,
        backStack = backstack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.LoginRoute> {
                val viewModel: LoginViewModel = viewModel(
                    factory = LoginViewModelFactory(userPreferences)
                )
                LoginRoot(
                    viewModel = viewModel,
                    backstack = backstack
                )
            }
            entry<Route.VehiclesListRoute> {
                val viewModel: MainScreenViewModel = viewModel(
                    factory = MainScreenViewModelFactory(userPreferences)
                )
                MainRoot(
                    viewModel = viewModel,
                    backstack = backstack,
                    username = it.userName,
                )
            }
        }
    )
}