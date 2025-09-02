package com.example.actividad_2_ddapm

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
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
import com.example.actividad_2_ddapm.ui.theme.Actividad_2_DDAPMTheme
import com.example.actividad_2_ddapm.ui.theme.Green2D0
import com.example.actividad_2_ddapm.viewModel.CampusViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.actividad_2_ddapm.model.LoginResponse
import com.example.actividad_2_ddapm.viewModel.LoginViewModel


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividad_2_DDAPMTheme {
                var mostrar by remember { mutableStateOf(false) }
                var campus by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var nombre by remember { mutableStateOf("") }
                var apellido by remember { mutableStateOf("") }
                var matricula by remember { mutableStateOf("") }
                var contraseña by remember { mutableStateOf("") }
                //var campuses2 = RetrofitClient.instance.getCampusNames()
                //val campuses = listOf("Campus Cumbres", "Campus San Nicolas", "Campus Las torres")
                //var campuses: List<Campuses> by remember { mutableStateOf(listOf<Campuses>()) }
                var expanded by remember { mutableStateOf(false) }
                //var campusesList by remember { mutableStateOf(listOf<String>()) }
                val campusViewModel: CampusViewModel = viewModel()
                val campuses by campusViewModel.campuses
                var isLoading by remember { mutableStateOf(true) }
                val scope = rememberCoroutineScope()


                LaunchedEffect(Unit) {
                    campusViewModel.loadCampuses()
                }


                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { mostrar = true},
                            containerColor = Color(0xFF00514A),
                            contentColor = Color(color = 0xFFFFFFFF)) {
                            Text("+",
                                fontSize = 35.sp,)
                        }
                    },
                    topBar = {
                    TopAppBar(
                        title = { Text("Tec Milenio") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF00514A),
                            titleContentColor = Color(color = 0xFFFFFFFF),
                        )
                    )
                }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize()
                            .padding(innerPadding),
                        color = Green2D0,
                    ){
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding),
                        )
                        if(mostrar){
                            Dialog(onDismissRequest = { mostrar = false}){
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(color = 0xFF00A499)),
                                    contentAlignment = Alignment.Center,
                                ){
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ){
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Text(
                                            text = "Crear nueva cuenta",
                                            color = Color(color = 0xFFFFFFFF),
                                            fontSize = 30.sp,
                                            textAlign = TextAlign.Center
                                        )
                                        if (campuses.isEmpty()) {
                                            Text("Cargando campus...")
                                        } else {
                                            ExposedDropdownMenuBox(
                                                expanded = expanded,
                                                onExpandedChange = { expanded = !expanded },
                                                modifier = Modifier.fillMaxWidth().padding(16.dp)
                                            ) {
                                                TextField(
                                                    value = campus,
                                                    onValueChange = {},
                                                    readOnly = true,
                                                    label = { Text("Campus") },
                                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                                    modifier = Modifier
                                                        .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                                                        .padding(6.dp)
                                                )
                                                ExposedDropdownMenu(
                                                    expanded = expanded,
                                                    onDismissRequest = { expanded = false }
                                                ) {
                                                    campuses.forEach { campusOption ->
                                                        DropdownMenuItem(
                                                            text = { Text(campusOption.campusName) },
                                                            onClick = {
                                                                campus = campusOption.campusName
                                                                expanded = false
                                                            }
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        TextField(
                                            modifier = Modifier.padding(16.dp),
                                            value = email,
                                            onValueChange = { email = it },
                                            label = { Text("Email") }
                                        )
                                        TextField(
                                            modifier = Modifier.padding(16.dp),
                                            value = nombre,
                                            onValueChange = { nombre = it },
                                            label = { Text("Nombre") }
                                        )
                                        TextField(
                                            modifier = Modifier.padding(16.dp),
                                            value = apellido,
                                            onValueChange = { apellido = it },
                                            label = { Text("Apellidos") }
                                        )
                                        TextField(
                                            modifier = Modifier.padding(16.dp),
                                            value = matricula,
                                            onValueChange = { matricula = it },
                                            label = { Text("Matricula") }
                                        )
                                        TextField(
                                            modifier = Modifier.padding(16.dp),
                                            value = contraseña,
                                            visualTransformation = PasswordVisualTransformation(),
                                            onValueChange = { contraseña = it },
                                            label = { Text("Contraseña") }
                                        )
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ){
                                            Button(
                                                onClick = {mostrar = false},
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color(0xFF00A499),
                                                    contentColor = Color(0xFFFFFFFF)
                                                )
                                            ) {
                                                Text(text = "Cancel")
                                            }
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Button(
                                                onClick = {mostrar = false},
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
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, loginViewModel: LoginViewModel = viewModel<LoginViewModel>()) {
    val context = LocalContext.current
    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    val loginResponseState by loginViewModel.login(correo, contraseña).observeAsState()

    // Estado para controlar cuando mostrar el Toast
    var toastShown by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth().padding(16.dp)
            .verticalScroll(rememberScrollState())
        //.background(Green2D0)
    ) {
        Image(
            painter = painterResource(id = R.drawable.tecmi_imagen),
            contentDescription = "Logo de la app",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth().padding(65.dp)
                .height(260.dp)
        )
        Text(
            text = "Hawk Connect",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Column() {
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo Electronico") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = contraseña,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { contraseña = it },
                label = { Text("Contraseña") }
            )
            Button(
                onClick = {
                    toastShown = true // Reiniciamos para que pueda mostrar Toast
                    loginViewModel.login(correo, contraseña)
                },
                modifier = Modifier
                    .fillMaxWidth().padding(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00514A),
                    contentColor = Color(0xFFFFFFFF)
                )
            ) {
                Text(text = "Entrar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            loginResponseState?.let { response ->
                if (toastShown) {
                    val user = response.d?.users?.firstOrNull()
                    if (user != null) {
                        Toast.makeText(context, "Bienvenido ${user.name} ${user.lastName}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                    toastShown = false
                }

//                // Mostrar datos si hay usuario
//                response.d?.users?.firstOrNull()?.let { user ->
//                    Text("Nombre: ${user.name} ${user.lastName}")
//                    Text("Correo: ${user.email}")
//                    Text("Campus: ${user.campus}")
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Actividad_2_DDAPMTheme {
        Greeting("Android")
    }
}