package com.example.moblab4_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.atan

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4()
        }
    }
}

@Composable
fun Lab4() {
    var xText by remember { mutableStateOf("") }
    var nText by remember { mutableStateOf("") }
    var otvet by remember { mutableStateOf("") }

    val fon = Color(0xFFF1F1F1)
    val knopka = Color(red = 0x3F, green = 0x6E, blue = 0xC8, alpha = 0xFF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fon)
            .padding(start = 20.dp, top = 50.dp, end = 20.dp, bottom = 20.dp)
    ) {
        Text("Лабораторная работа №4, вариант 12", fontSize = 20.sp)
        Text(
            "arctg(x) = -π/2 - 1/x + 1/(3x³) - 1/(5x⁵) + ...",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
        )

        OutlinedTextField(
            value = xText,
            onValueChange = { xText = it },
            label = { Text("x (x < -1)") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nText,
            onValueChange = { nText = it },
            label = { Text("N (кол-во членов)") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            "Посчитать",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .background(knopka, RoundedCornerShape(8.dp))
                .clickable {
                    val x = xText.replace(',', '.').toDoubleOrNull()
                    val n = nText.toIntOrNull()
                    if (x == null || x >= -1) {
                        otvet = "Ошибка: x должен быть меньше -1"
                    } else if (n == null || n < 0 || n > SeriesCalculator.MAX_N) {
                        otvet = "Ошибка: N от 0 до 1000000"
                    } else {
                        val s = SeriesCalculator.calculate(x, n)
                        otvet = "Сумма ряда: " + "%.6f".format(s) +
                                "\narctg(x): " + "%.6f".format(atan(x))
                    }
                }
                .padding(12.dp)
        )

        Text(
            otvet,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .border(width = 2.dp, color = Color.Black)
                .padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Lab4Preview() {
    Lab4()
}
