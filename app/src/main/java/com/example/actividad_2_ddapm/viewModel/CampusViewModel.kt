package com.example.actividad_2_ddapm.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.actividad_2_ddapm.network.RetrofitClient
import com.example.actividad_2_ddapm.model.Campuses
import com.example.actividad_2_ddapm.model.Friend
import com.example.actividad_2_ddapm.model.FriendsFilterData
import com.example.actividad_2_ddapm.model.FriendsFilterRequest
import com.example.actividad_2_ddapm.model.FriendsFilterResponse
import com.example.actividad_2_ddapm.model.FriendsListDatos
import com.example.actividad_2_ddapm.model.FriendsListRequest
import com.example.actividad_2_ddapm.model.LoginDatos
import com.example.actividad_2_ddapm.model.LoginRequest
import com.example.actividad_2_ddapm.model.LoginResponse
import com.example.actividad_2_ddapm.model.NewUserDatos
import com.example.actividad_2_ddapm.model.NewUserRequest
import com.example.actividad_2_ddapm.model.PostData
import com.example.actividad_2_ddapm.model.PostFilterData
import com.example.actividad_2_ddapm.model.PostFilterRequest
import com.example.actividad_2_ddapm.model.PostFilterResponse
import com.example.actividad_2_ddapm.model.PostRequest
import com.example.actividad_2_ddapm.model.PostResponse
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

class FriendsViewModel : ViewModel() {

    private val _selectedFriends = mutableStateListOf<Int>()
    val selectedFriends: List<Int> get() = _selectedFriends

    fun setInitialFriends(friends: List<Friend>) {
        _selectedFriends.clear()
        _selectedFriends.addAll(friends.filter { it.isFriend.toBoolean() }.map { it.userId })
    }


    fun toggleFriendSelection(userId: Int, isSelected: Boolean) {
        if (isSelected) {
            if (!_selectedFriends.contains(userId)) {
                _selectedFriends.add(userId)
            }
        } else {
            _selectedFriends.remove(userId)
        }
        Log.d("FriendsViewModel", "Seleccionados: ${getFriendsAsString()}")
    }

    fun getFriendsAsString(): String {
        return _selectedFriends.joinToString(",")
    }

    fun sendFriends(studentId: Int) = liveData(Dispatchers.IO) {
        try {
            val friendsListDatos = FriendsListDatos(studentId, getFriendsAsString())
            val request = FriendsListRequest(friendsListDatos)
            val response = RetrofitClient.instance.postNewFriendsListRequest(request)
            emit(response)
        } catch (e: Exception) {
            emit(null)
        }
    }
}

class PostFilterViewModel : ViewModel() {

    private val _PostFilterResponse = MutableLiveData<PostFilterResponse?>()
    val PostFilterResponse: LiveData<PostFilterResponse?> get()= _PostFilterResponse

    fun PostsRequest(loggedUserId: Int) = liveData(Dispatchers.IO) {
        Log.d("VIEWMODEL_SEARCH", "Search called")
        try {
            val PostFilterRequest = PostFilterData(loggedUserId)
            val request = PostFilterRequest(PostFilterRequest)
            //Log.d("LoginViewModel", "Haciendo login con $username / $password")


            // Hacemos la llamada al API
            val response = RetrofitClient.instance.newPostFilterRequest(request)
            Log.d("API_RESPONSE", response.toString())
            //Log.d("API_RESPONSE", response.toString())

            emit(response)
            _PostFilterResponse.postValue(response)
        } catch (e: Exception) {
            //Log.e("API_ERROR", e.toString())
            emit(null) // En caso de error
        }
    }
}

class PostViewModel : ViewModel() {

    private val _PostResponse = MutableLiveData<PostResponse?>()
    val PostResponse: LiveData<PostResponse?> get()= _PostResponse

    fun MyPostRequest(loggedUserId: Int, message1: String) = liveData(Dispatchers.IO) {
        Log.d("VIEWMODEL_SEARCH", "Search called")
        try {
            val PostRequest = PostData(loggedUserId, message1)
            val request = PostRequest(PostRequest)
            //Log.d("LoginViewModel", "Haciendo login con $username / $password")


            // Hacemos la llamada al API
            val response = RetrofitClient.instance.newPostRequest(request)
            Log.d("API_RESPONSE", response.toString())
            //Log.d("API_RESPONSE", response.toString())

            emit(response)
//            _PostResponse.postValue(response)
        } catch (e: Exception) {
            //Log.e("API_ERROR", e.toString())
            emit(null) // En caso de error
        }
    }
}
