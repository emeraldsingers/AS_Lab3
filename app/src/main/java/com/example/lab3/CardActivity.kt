package com.example.lab3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val bgColor = Color(0xFF121212)
val blockColor = Color(0xFF1E1E1E)
val greenColor = Color(0xFF4CAF1E)

class CardActivity : ComponentActivity() {
    var pinOk by mutableStateOf(false)
    var pinText by mutableStateOf("")
    var errorText by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if (pinOk) {
                BankScreen(onBannerClick = {
                    startActivity(Intent(this@CardActivity, AdActivity::class.java))
                })
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(bgColor)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_privat),
                        contentDescription = null,
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                    )
                    Text(text = "Введіть PIN-код", color = Color.White, fontSize = 24.sp)
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = pinText,
                        onValueChange = { pinText = it },
                        label = { Text(text = "PIN (1234)") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.LightGray,
                            focusedBorderColor = greenColor,
                            unfocusedBorderColor = Color.Gray,
                            cursorColor = Color.White
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        singleLine = true
                    )
                    Text(text = errorText, color = Color.Red)
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = greenColor),
                        onClick = {
                            if (pinText == "1234") {
                                pinOk = true
                            } else {
                                errorText = "Неправильний PIN"
                            }
                        }
                    ) {
                        Text(text = "Увійти")
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        pinOk = false
        pinText = ""
        errorText = ""
    }
}

@androidx.compose.runtime.Composable
fun BankScreen(onBannerClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFCCE5FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "М", color = Color(0xFF1565C0), fontSize = 22.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(R.drawable.logo_privat),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF3F5F8F))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Максим Лапіка",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(text = "Картка Універсальна", color = Color.White, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "•••• 3700   08 / 30", color = Color.White, fontSize = 16.sp)
                }
                Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                    Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(Color.Red))
                    Box(modifier = Modifier.size(28.dp).clip(CircleShape).background(Color(0xFFFF9800)))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "9 999.99 UAH",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoRow("Кредитний ліміт", "0.00")
            InfoRow("Власні кошти", "9 999.99")

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(blockColor)
                    .padding(16.dp)
            ) {
                Text(text = "Дані клієнта", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow("Ім'я", "Максим")
                InfoRow("Прізвище", "Лапіка")
                InfoRow("Вік", "18")
                InfoRow("ID клієнта", "100500")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF003366))
                    .clickable { onBannerClick() }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Що нового у Приват24", color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = "Оновлений та кращий", color = Color.White)
                }
                Text(text = ">", color = Color.White, fontSize = 22.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionButton(Icons.Default.KeyboardArrowDown, "Поповнити")
                ActionButton(Icons.Default.Send, "Переказати")
                ActionButton(Icons.Default.Phone, "Мобільний")
                ActionButton(Icons.Default.ShoppingCart, "Платежі")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(blockColor)
                    .padding(16.dp)
            ) {
                Text(text = "Історія", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                HistoryRow("Варус", "27 листопада, 02:11", "-545.45", R.drawable.logo_varus)
                HistoryRow("АТБ", "26 листопада, 18:24", "-210.00", R.drawable.logo_atb)
                HistoryRow("Поповнення", "25 листопада, 10:05", "+1 000.00", R.drawable.logo_privat)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(blockColor)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MenuItem(Icons.Default.Home, "Головний", greenColor)
            MenuItem(Icons.Default.Info, "Кредити", Color.LightGray)
            MenuItem(Icons.Default.Email, "Конверти", Color.LightGray)
            MenuItem(Icons.Default.Notifications, "Привіт", Color.LightGray)
            MenuItem(Icons.Default.Menu, "Ще", Color.LightGray)
        }
    }
}

@androidx.compose.runtime.Composable
fun InfoRow(name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name, color = Color.White)
        Text(text = value, color = Color.White)
    }
}

@androidx.compose.runtime.Composable
fun ActionButton(icon: ImageVector, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color(0xFF333333)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = text, tint = Color.White)
        }
        Text(text = text, color = Color.White, fontSize = 12.sp)
    }
}

@androidx.compose.runtime.Composable
fun HistoryRow(name: String, date: String, price: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, color = Color.White)
            Text(text = date, color = Color.Gray, fontSize = 12.sp)
        }
        Text(text = price, color = Color.White)
    }
}

@androidx.compose.runtime.Composable
fun MenuItem(icon: ImageVector, text: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = text, tint = color)
        Text(text = text, color = color, fontSize = 12.sp)
    }
}
