package com.example.actividad_2_ddapm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.actividad_2_ddapm.ui.theme.Actividad_2_DDAPMTheme
import com.example.actividad_2_ddapm.ui.theme.Green2D0
import com.example.actividad_2_ddapm.viewModel.CampusViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.MutableLiveData
import com.example.actividad_2_ddapm.model.FriendsFilterResponse
import com.example.actividad_2_ddapm.model.LoginResponse
import com.example.actividad_2_ddapm.viewModel.FriendsFilterViewModel
import com.example.actividad_2_ddapm.viewModel.FriendsViewModel
import com.example.actividad_2_ddapm.viewModel.LoginViewModel

class MainActivityMyFriends : ComponentActivity() {
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
                        Greeting3(
                            name = "My Friends",
                            studentId = studentId,
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting3(name: String, studentId: Int,  modifier: Modifier = Modifier, FriendsFilterViewModel: FriendsFilterViewModel = viewModel<FriendsFilterViewModel>()) {
    var expanded by remember { mutableStateOf(false) }
    val campusViewModel: CampusViewModel = viewModel()
    var campus by remember { mutableStateOf("") }
    var idCampus by remember { mutableIntStateOf(0) }
    val campuses by campusViewModel.campuses
    var nombre by remember { mutableStateOf("") }
    //var buscar by remember { mutableStateOf(false) }
    var doFilter by remember { mutableStateOf(false) }
    var totaldepersonas by remember { mutableStateOf(0) }
    var totaldeamigos by remember { mutableStateOf(0) }
    val friendsViewModel: FriendsViewModel = viewModel()
    val sendResponse by friendsViewModel.sendFriends(studentId).observeAsState()
    val context = LocalContext.current
    var mostrarConteo by remember { mutableStateOf(true) }
    var nombreSearch by remember { mutableStateOf("") }
    var idCampusSearch by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        campusViewModel.loadCampuses()
    }

    val filterLiveData = remember(doFilter, studentId, idCampusSearch,nombreSearch) {
        if (doFilter) {
            FriendsFilterViewModel.Search(studentId, idCampusSearch, nombreSearch)

        } else {
            MutableLiveData<FriendsFilterResponse?>(null) // <-- nunca null directamente
        }
    }

    val filterResponseState by filterLiveData.observeAsState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "MY FRIENDS",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                if (campuses.isEmpty()) {
                    Text("Cargando campus...")
                } else {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
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
                                        idCampus = campusOption.campusID
                                        //Log.d("LoginViewModel", "Haciendo login con $idCampus")
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                TextField(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
            }
            //Text(text = "hola")
            IconButton(onClick = {
                doFilter = true
                nombreSearch = nombre
                idCampusSearch = idCampus
//                if(doFilter == false){
//                    nombre = ""
//                    campus = ""
//                    mostrarConteo = false
//                }else{
//                    mostrarConteo = true
//                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Text(
                text = "Amigos $totaldeamigos de $totaldepersonas",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier.padding(12.dp))
            Button(
                onClick = {
                    friendsViewModel.sendFriends(studentId)
                    sendResponse?.let { response ->
                        if(response != null){
                            Toast.makeText(context, "Status de amigos guardados: ${response.d.executeResult} , ${response.d.message}", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Error: ${response.d.executeResult} , ${response.d.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                shape = RectangleShape,
                        modifier = Modifier
                    .fillMaxWidth().padding(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00514A),
                    contentColor = Color(0xFFFFFFFF)
                )
            ) {
                Text(text = "Guardar")
            }
        }
        filterResponseState?.let { response ->
            val friends = response.d.friends
//            val existingFriends = filterResponseState?.d?.friends
//            existingFriends?.forEach { friend ->
//                if(friend.isFriend.toBoolean()) {
//                    friendsViewModel.toggleFriendSelection(friend.userId, true)
//                }
//            }
            friends.forEach { friendOption ->
                val initialChecked = friendOption.isFriend.toBoolean()

                var isChecked by remember(friendOption.studentNumber) {
                    mutableStateOf(initialChecked)
                }
                if(isChecked) {
                    friendsViewModel.toggleFriendSelection(friendOption.userId, true)
                }else{
                    friendsViewModel.toggleFriendSelection(friendOption.userId, false)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checked ->
                            isChecked = checked
                            friendsViewModel.toggleFriendSelection(friendOption.userId, checked)
                        },
                        modifier = Modifier.size(40.dp)
                    )
                    Column {
                        Text(text = "Nombre: ${friendOption.completeName}")
                        Text(text = "Matrícula: ${friendOption.studentNumber}")
                        Text(text = "Campus: ${friendOption.campus}")
                        Text(text = "Es Amigo?: ${friendOption.isFriend}")
                        friendOption.userId
                        totaldepersonas = if(mostrarConteo){
                            filterResponseState?.d?.friends?.size ?: 0
                        }else{
                            0
                        }
                        totaldeamigos = if(mostrarConteo){
                            filterResponseState?.d?.friends?.count { it.isFriend.toBoolean() } ?: 0
                        }else{
                            0
                        }
                        //personas = personas + 1
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    Actividad_2_DDAPMTheme {
        Greeting3("Agustin",1)
    }
}