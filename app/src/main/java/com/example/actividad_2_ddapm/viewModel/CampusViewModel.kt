package com.example.actividad_2_ddapm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.actividad_2_ddapm.network.RetrofitClient
import com.example.actividad_2_ddapm.model.Campuses

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
