package com.example.actividad_2_ddapm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.actividad_2_ddapm.model.FriendsFilterResponse
import com.example.actividad_2_ddapm.model.PostFilterResponse
import com.example.actividad_2_ddapm.ui.theme.Actividad_2_DDAPMTheme
import com.example.actividad_2_ddapm.ui.theme.Black
import com.example.actividad_2_ddapm.ui.theme.Green2D0
import com.example.actividad_2_ddapm.viewModel.CampusViewModel
import com.example.actividad_2_ddapm.viewModel.FriendsFilterViewModel
import com.example.actividad_2_ddapm.viewModel.LoginViewModel
import com.example.actividad_2_ddapm.viewModel.NewUserViewModel
import com.example.actividad_2_ddapm.viewModel.PostFilterViewModel
import androidx.compose.foundation.lazy.items
import com.example.actividad_2_ddapm.ui.theme.white
import com.example.actividad_2_ddapm.viewModel.PostViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner


class MainActivitySecondScreen : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name") ?: "Desconocido"
        val lastName = intent.getStringExtra("lastName") ?: ""
        val studentId = intent.getIntExtra("studentId", -1)
        enableEdgeToEdge()
        setContent {
            Actividad_2_DDAPMTheme {
                var mostrar by remember { mutableStateOf(false) }
                var postMessage by remember { mutableStateOf("") }
                val context = LocalContext.current
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {mostrar = true},
                            containerColor = Color(0xFF00514A),
                            contentColor = Color(color = 0xFFFFFFFF)) {
                            Text("+",
                                fontSize = 35.sp,)
                        }
                    },
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
                        Greeting2(
                            name = name,
                            lastname = lastName,
                            studentId = studentId,
                            modifier = Modifier.padding(innerPadding),
                        )
                        if(mostrar){
                            Popup(mostrar, onDismiss = { mostrar = false }, postMessage = postMessage, studentId, onMessageChange = { postMessage = it })
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun Popup(mostrar: Boolean, onDismiss:() -> Unit, postMessage: String, studentId: Int, onMessageChange: (String) -> Unit){
    val postViewModel: PostViewModel = viewModel()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    if(mostrar){
        Dialog(onDismissRequest = { onDismiss() }){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(color = 0xFF00A499)),
                contentAlignment = Alignment.Center,
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Spacer(Modifier.padding(5.dp))
                    Text(
                        text = "Nuevo Post",
                        color = Color(color = 0xFFFFFFFF),
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                    TextField(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(300.dp),
                        value = postMessage,
                        onValueChange = { onMessageChange(it) },
                        label = { Text("Postea como te sientes hoy...") }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Button(
                            onClick = {onDismiss()},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF00A499),
                                contentColor = Color(0xFFFFFFFF)
                            )
                        ) {
                            Text(text = "Cancel")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {onDismiss()
                                      postViewModel.MyPostRequest(
                                          loggedUserId = studentId,
                                          message1 = postMessage
                                      ).observe(lifecycleOwner) { response ->
                                          if (response != null) {
                                              Toast.makeText(
                                                  context,
                                                  "Post status: ${response?.d?.executeResult}",
                                                  Toast.LENGTH_LONG
                                              ).show()
                                              Log.d("Mensaje de error del post", "ERROR: ${response?.d?.message}")
                                          } else {
                                              Toast.makeText(
                                                  context,
                                                  "Error: ${response?.d?.message}",
                                                  Toast.LENGTH_LONG
                                              ).show()
                                          }
                                      }},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF00A499),
                                contentColor = Color(0xFFFFFFFF)
                            )
                        ) {
                            Text(text = "Create")
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun Greeting2(name: String, lastname: String, studentId: Int,  modifier: Modifier = Modifier, PostFilterViewModel: PostFilterViewModel = viewModel<PostFilterViewModel>()) {
    var doFilter by remember { mutableStateOf(true) }
    val filterLiveData = remember(doFilter, studentId) {
        if (doFilter) {
            PostFilterViewModel.PostsRequest(studentId)

        } else {
            MutableLiveData<PostFilterResponse?>(null) // <-- nunca null directamente
        }
    }

    val filterPostsResponseState by filterLiveData.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "FEED",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Bienvenido $name $lastname! , ID: $studentId",
            fontSize = 20.sp
        )
        Text(
            text = "Posts mas recientes:",
            fontSize = 20.sp
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .weight(1f)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            filterPostsResponseState?.let { response ->
                val posts = response.d.posts
                if (posts.isEmpty()) {
                    item { Text("No hay posts para mostrar...") }
                } else {
                    items(posts) { post ->
                        PostItem(
                            completeName = post.completeName,
                            message = post.message,
                            timeStamp = post.timeStamp
                        )
                        Spacer(Modifier.padding(5.dp))
                    }
                }
            } ?: item { Text("Esperando datos...") }
            }
        }
    }
@Composable
fun PostItem(
    completeName: String,
    message: String,
    timeStamp: String
) {
    Column(
        modifier = Modifier
            .background(color = white)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = completeName,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = message,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
        Text(
            text = timeStamp,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Actividad_2_DDAPMTheme {
        Greeting2("Agustin","Villanueva", 1)
    }
}
