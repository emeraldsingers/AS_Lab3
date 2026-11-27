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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Лабораторна робота 3", fontSize = 28.sp)

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { context.startActivity(Intent(context, Task1Activity::class.java)) }
        ) {
            Text(text = "Завдання 1 - Ліхтарик")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { context.startActivity(Intent(context, CardActivity::class.java)) }
        ) {
            Text(text = "Завдання 2 - Банківська картка")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { context.startActivity(Intent(context, ProfileActivity::class.java)) }
        ) {
            Text(text = "Завдання 3 - Особиста сторінка")
        }
    }
}
