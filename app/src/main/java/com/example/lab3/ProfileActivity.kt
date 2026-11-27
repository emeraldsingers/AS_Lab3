package com.example.lab3

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var name by rememberSaveable { mutableStateOf("Максим") }
            var surname by rememberSaveable { mutableStateOf("Лапіка") }
            var avatarUri by rememberSaveable { mutableStateOf<Uri?>(null) }
            var loggedIn by rememberSaveable { mutableStateOf(false) }
            var login by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            var errorText by rememberSaveable { mutableStateOf("") }

            val friendNames = listOf("Надія", "Марія", "Кирило", "Евеліна", "Артур")
            val friendPhotos = listOf(R.drawable.friend1, R.drawable.friend2, R.drawable.friend3, R.drawable.friend4, R.drawable.friend5)
            val smallPhotos = listOf(R.drawable.photo1, R.drawable.photo2, R.drawable.photo3, R.drawable.photo4)

            val nameLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                val data = result.data
                if (result.resultCode == RESULT_OK && data != null) {
                    name = data.getStringExtra("name") ?: name
                    surname = data.getStringExtra("surname") ?: surname
                }
            }

            val avatarLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.GetContent()
            ) { uri ->
                if (uri != null) {
                    avatarUri = uri
                }
            }

            val avatarBitmap = avatarUri?.let {
                val stream = contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(stream)
                stream?.close()
                bitmap
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    if (avatarBitmap != null) {
                        Image(
                            bitmap = avatarBitmap.asImageBitmap(),
                            contentDescription = "photo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.me),
                            contentDescription = "photo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (photo in smallPhotos) {
                            Image(
                                painter = painterResource(photo),
                                contentDescription = "photo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(70.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("$name $surname", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text("@asoqwer", fontSize = 16.sp, color = Color.Gray)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { avatarLauncher.launch("image/*") }
                        ) {
                            Text("Змінити аватар")
                        }
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                val intent = Intent(this@ProfileActivity, EditNameActivity::class.java)
                                intent.putExtra("name", name)
                                intent.putExtra("surname", surname)
                                nameLauncher.launch(intent)
                            }
                        ) {
                            Text("Змінити ім'я")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Place, contentDescription = "place")
                        Spacer(modifier = Modifier.size(4.dp))
                        Text("Україна, Павлоград", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Interests", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    InterestRow(Icons.Default.Build, "Programming")
                    InterestRow(Icons.Default.Favorite, "Music")
                    InterestRow(Icons.Default.Star, "OpenUtau")

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Friends: 67", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (i in 0..4) {
                            Column(
                                modifier = Modifier.width(64.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(friendPhotos[i]),
                                    contentDescription = friendNames[i],
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    friendNames[i],
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                if (!loggedIn) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .clickable { }
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Вхід", fontSize = 28.sp)
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = login,
                            onValueChange = { login = it },
                            label = { Text(text = "Логін") },
                            singleLine = true
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(text = "Пароль") },
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true
                        )
                        Text(text = errorText, color = Color.Red)
                        Button(onClick = {
                            if (login.isEmpty()) {
                                errorText = "Введіть логін"
                            } else if (password.length < 4) {
                                errorText = "Пароль мінімум 4 символи"
                            } else {
                                errorText = ""
                                loggedIn = true
                            }
                        }) {
                            Text(text = "Увійти")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InterestRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text, fontSize = 16.sp)
    }
}
