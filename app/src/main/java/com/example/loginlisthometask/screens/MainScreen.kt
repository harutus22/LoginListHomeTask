package com.example.loginlisthometask.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.loginlisthometask.R
import com.example.loginlisthometask.viewmodels.event.*
import com.example.loginlisthometask.composables.VehicleItem
import com.example.loginlisthometask.viewmodels.states.VehicleState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    uiState: VehicleState,
    userName: String,
    onEvent: (event: MainScreenEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(LoadVehicles)
    }
    Column(modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(R.string.user, userName))
                IconButton(onClick = {
                    onEvent(Logout)
                }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.logout),
                        contentDescription = stringResource(R.string.log_out)
                    )
                }
            }
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.isLoading) {
                item() {
                    CircularProgressIndicator(
                        modifier = Modifier.size(80.dp)
                    )
                }
            } else if (uiState.list.isNotEmpty()) {
                items(
                    uiState.list,
                    key = { vehicle -> vehicle.vin }
                ) { vehicle ->
                    VehicleItem(vehicle)
                }
            }
        }
    }
}