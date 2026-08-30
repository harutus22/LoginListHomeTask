package com.example.loginlisthometask.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginlisthometask.R
import com.example.loginlisthometask.data.Vehicle
import kotlin.random.Random

@Composable
fun VehicleItem(vehicle: Vehicle) {
    var backgroundColor by rememberSaveable(stateSaver = vehicle.colorSaver) {
        mutableStateOf(vehicle.color)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                backgroundColor = randomColor()
            }
            .padding(4.dp)
            .background(backgroundColor),
    ) {
        VehicleFieldText(
            stringResource(R.string.vin, vehicle.vin),
        )
        VehicleFieldText(
            stringResource(R.string.model, vehicle.model)
        )
    }
}

private fun randomColor(): Color {
    val colorLimit = 256
    val red = Random.nextInt(colorLimit)
    val green = Random.nextInt(colorLimit)
    val blue = Random.nextInt(colorLimit)
    return Color(red, green, blue)
}

@Composable
fun VehicleFieldText(
    value: String,
    modifier: Modifier = Modifier,
) {
    Text(
        value, modifier
            .padding(horizontal = 6.dp, vertical = 3.dp),
        color = Color.White
    )
}

@Composable
fun VehicleColorValue(
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            stringResource(R.string.color), Modifier
                .padding(horizontal = 6.dp, vertical = 3.dp),
            color = Color.White
        )
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .width(80.dp)
                .height(20.dp)
                .padding(vertical = 2.dp)
                .background(color)
        )
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    VehicleItem(Vehicle(11234564, "Mercedez campo", Color.White))
}