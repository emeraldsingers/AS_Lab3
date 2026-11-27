package com.example.lab3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Task1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task1Screen()
        }
    }
}

@Composable
fun Task1Screen() {
    val context = LocalContext.current
    val colorList = listOf(
        "Білий" to 0xFFFFFFFF,
        "Червоний" to 0xFFFF0000,
        "Синій" to 0xFF0000FF,
        "Зелений" to 0xFF00FF00,
        "Ціан" to 0xFF00FFFF,
        "Світло-сірий" to 0xFFD3D3D3,
        "Маджента" to 0xFFFF00FF
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Екранний ліхтарик", fontSize = 28.sp)
        Text(text = "Обери колір екрану")

        for (item in colorList) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val intent = Intent(context, ColorActivity::class.java)
                    intent.putExtra("color", item.second)
                    context.startActivity(intent)
                }
            ) {
                Text(text = item.first)
            }
        }
    }
}
