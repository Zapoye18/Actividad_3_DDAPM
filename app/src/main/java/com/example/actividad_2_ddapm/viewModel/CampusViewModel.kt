package com.example.actividad_2_ddapm.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.actividad_2_ddapm.network.RetrofitClient
import com.example.actividad_2_ddapm.model.Campuses
import com.example.actividad_2_ddapm.model.FriendsFilterData
import com.example.actividad_2_ddapm.model.FriendsFilterRequest
import com.example.actividad_2_ddapm.model.FriendsFilterResponse
import com.example.actividad_2_ddapm.model.LoginDatos
import com.example.actividad_2_ddapm.model.LoginRequest
import com.example.actividad_2_ddapm.model.LoginResponse
import com.example.actividad_2_ddapm.model.NewUserDatos
import com.example.actividad_2_ddapm.model.NewUserRequest
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

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse

    fun login(username: String, password: String) = liveData(Dispatchers.IO) {
        try {
            val loginDatos2 = LoginDatos(username, password)
            val request = LoginRequest(loginDatos2)
            //Log.d("LoginViewModel", "Haciendo login con $username / $password")


            // Hacemos la llamada al API
            val response = RetrofitClient.instance.postLoginRequests(request)
            //Log.d("API_RESPONSE", response.toString())

            _loginResponse.postValue(response)
            emit(response)
        } catch (e: Exception) {
            //Log.e("API_ERROR", e.toString())
            emit(null) // En caso de error
        }
    }
}

class NewUserViewModel : ViewModel() {

    fun Register(campusID: Int, email1: String, nombre: String, apellido: String, matricula: Int, contraseña: String) = liveData(Dispatchers.IO) {
        try {
            val NewUserRequest2 = NewUserDatos(nombre, apellido, email1, contraseña, matricula, campusID)
            val request = NewUserRequest(NewUserRequest2)
            //Log.d("LoginViewModel", "Haciendo login con $username / $password")


            // Hacemos la llamada al API
            val response = RetrofitClient.instance.postNewUserRequest(request)
            //Log.d("API_RESPONSE", response.toString())

            emit(response)
        } catch (e: Exception) {
            //Log.e("API_ERROR", e.toString())
            emit(null) // En caso de error
        }
    }
}

class FriendsFilterViewModel : ViewModel() {

    private val _FriendsFilterResponse = MutableLiveData<FriendsFilterResponse?>()
    val FriendsFilterResponse: LiveData<FriendsFilterResponse?> get()= _FriendsFilterResponse

    fun Search(loggedUserId: Int, campusId: Int, name: String) = liveData(Dispatchers.IO) {
        Log.d("VIEWMODEL_SEARCH", "Search called")
        try {
            val FriendsFilterData = FriendsFilterData(loggedUserId, campusId, name)
            val request = FriendsFilterRequest(FriendsFilterData)
            //Log.d("LoginViewModel", "Haciendo login con $username / $password")


            // Hacemos la llamada al API
            val response = RetrofitClient.instance.postNewFriendsFilterRequest(request)
            Log.d("API_RESPONSE", response.toString())
            //Log.d("API_RESPONSE", response.toString())

            emit(response)
            _FriendsFilterResponse.postValue(response)
        } catch (e: Exception) {
            //Log.e("API_ERROR", e.toString())
            emit(null) // En caso de error
        }
    }
}
