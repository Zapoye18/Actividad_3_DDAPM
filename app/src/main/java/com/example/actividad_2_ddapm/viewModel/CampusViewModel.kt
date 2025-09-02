package com.example.actividad_2_ddapm.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.liveData
import com.example.actividad_2_ddapm.network.RetrofitClient
import com.example.actividad_2_ddapm.model.Campuses
import com.example.actividad_2_ddapm.model.LoginDatos
import com.example.actividad_2_ddapm.model.LoginRequest
import kotlinx.coroutines.Dispatchers

class CampusViewModel : ViewModel() {

    private val _campuses = mutableStateOf<List<Campuses>>(emptyList())
    val campuses: State<List<Campuses>> get() = _campuses

    fun loadCampuses() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getCampusNames()
                _campuses.value = response.campuses
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class LoginViewModel : ViewModel() {

    fun login(username: String, password: String) = liveData(Dispatchers.IO) {
        try {
            val loginDatos2 = LoginDatos(username, password)
            val request = LoginRequest(loginDatos2)
            //Log.d("LoginViewModel", "Haciendo login con $username / $password")


            // Hacemos la llamada al API
            val response = RetrofitClient.instance.postLoginRequests(request)
            //Log.d("API_RESPONSE", response.toString())

            emit(response)
        } catch (e: Exception) {
            //Log.e("API_ERROR", e.toString())
            emit(null) // En caso de error
        }
    }
}
