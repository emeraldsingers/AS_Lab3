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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class EditNameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val oldName = intent.getStringExtra("name") ?: ""
        val oldSurname = intent.getStringExtra("surname") ?: ""
        setContent {
            var name by rememberSaveable { mutableStateOf(oldName) }
            var surname by rememberSaveable { mutableStateOf(oldSurname) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Ім'я") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = surname,
                    onValueChange = { surname = it },
                    label = { Text(text = "Прізвище") }
                )
                Button(onClick = {
                    val data = Intent()
                    data.putExtra("name", name)
                    data.putExtra("surname", surname)
                    setResult(RESULT_OK, data)
                    finish()
                }) {
                    Text(text = "Зберегти")
                }
            }
        }
    }
}
