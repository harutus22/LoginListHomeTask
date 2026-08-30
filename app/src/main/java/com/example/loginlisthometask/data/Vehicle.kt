package com.example.loginlisthometask.data

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.ui.graphics.Color

data class Vehicle(
    val vin: Long,
    val model: String,
    val color: Color
) {
    val colorSaver = run<Vehicle, Saver<Color, Any>> {
        val redKey = "Red"
        val greenKey = "Green"
        val blueKey = "Blue"
        mapSaver(
            save = {
                mapOf(redKey to it.red, greenKey to it.green, blueKey to it.blue)
            },
            restore = { Color(it[redKey] as Float, it[greenKey] as Float, it[blueKey] as Float)}
        )
    }
}
