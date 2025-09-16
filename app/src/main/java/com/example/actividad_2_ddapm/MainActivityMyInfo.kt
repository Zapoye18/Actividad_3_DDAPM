package com.example.actividad_2_ddapm

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.actividad_2_ddapm.ui.theme.Actividad_2_DDAPMTheme
import com.example.actividad_2_ddapm.ui.theme.Green2D0

class MainActivityMyInfo : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name") ?: "Desconocido"
        val studentId = intent.getIntExtra("studentId", -1)
        val lastName = intent.getStringExtra("lastName") ?: ""
        enableEdgeToEdge()
        setContent {
            Actividad_2_DDAPMTheme {
                val context = LocalContext.current
                Scaffold(
                    topBar = {
                        var expanded by remember { mutableStateOf(false) }
                        TopAppBar(
                            title = { Text("Tec Milenio") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF00514A),
                                titleContentColor = Color(color = 0xFFFFFFFF),
                            ),
                            actions = {
                                IconButton(onClick = { expanded = true }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu",
                                        tint = Color.White
                                    )
                                }

                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("View Feed") },
                                        onClick = {
                                            expanded = false
                                            val intent = Intent(context, MainActivitySecondScreen::class.java)
                                            intent.putExtra("name", name)
                                            intent.putExtra("studentId", studentId)
                                            intent.putExtra("lastName", lastName)
                                            context.startActivity(intent)
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("My friends") },
                                        onClick = {
                                            expanded = false
                                            val intent = Intent(context, MainActivityMyFriends::class.java)
                                            intent.putExtra("name", name)
                                            intent.putExtra("studentId", studentId)
                                            intent.putExtra("lastName", lastName)
                                            context.startActivity(intent)
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("My info") },
                                        onClick = {
                                            expanded = false
                                            val intent = Intent(context, MainActivityMyInfo::class.java)
                                            intent.putExtra("name", name)
                                            intent.putExtra("studentId", studentId)
                                            intent.putExtra("lastName", lastName)
                                            context.startActivity(intent)
                                        }
                                    )
                                    Text("   --------------")
                                    DropdownMenuItem(
                                        text = { Text("Log Out") },
                                        onClick = {
                                            expanded = false
                                            val intent = Intent(context, MainActivity::class.java)
                                            context.startActivity(intent)
                                        }
                                    )
                                }
                            }
//                            navigationIcon = {
//                                IconButton(onClick = { expanded = true }) {
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.logo_menu_triple_barra), // tu ícono de 3 líneas (hamburger)
//                                        contentDescription = "Menu",
//                                        tint = Color.White
//                                    )
//                                }
//                            },
                        )
                    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize()
                            .padding(innerPadding),
                        color = Green2D0,
                    ){
                        Greeting4(
                            name = "My Info",
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Actividad_2_DDAPMTheme {
        Greeting4("Agustin")
    }
}