package com.example.actividad_2_ddapm.network

import com.example.actividad_2_ddapm.model.Campuses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.actividad_2_ddapm.model.CampusesResponse
import com.example.actividad_2_ddapm.model.LoginRequest
import com.example.actividad_2_ddapm.model.LoginResponse
import com.example.actividad_2_ddapm.model.NewUserRequest
import com.example.actividad_2_ddapm.model.NewUserResponse
import okhttp3.Response

interface ApiService {
    @GET("campus.aspx")
    suspend fun getCampusNames(): CampusesResponse

    @POST("users.aspx/UserLogin")
    suspend fun postLoginRequests(@Body request: LoginRequest): LoginResponse

    @POST("users.aspx/UserUI")
    suspend fun postNewUserRequest(@Body request: NewUserRequest): NewUserResponse

}
