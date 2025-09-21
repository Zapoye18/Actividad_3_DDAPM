package com.example.actividad_2_ddapm.network

import com.example.actividad_2_ddapm.model.Campuses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.actividad_2_ddapm.model.CampusesResponse
import com.example.actividad_2_ddapm.model.FriendsFilterRequest
import com.example.actividad_2_ddapm.model.FriendsFilterResponse
import com.example.actividad_2_ddapm.model.FriendsListRequest
import com.example.actividad_2_ddapm.model.FriendsListResponse
import com.example.actividad_2_ddapm.model.LoginRequest
import com.example.actividad_2_ddapm.model.LoginResponse
import com.example.actividad_2_ddapm.model.NewUserRequest
import com.example.actividad_2_ddapm.model.NewUserResponse
import com.example.actividad_2_ddapm.model.PostFilterRequest
import com.example.actividad_2_ddapm.model.PostFilterResponse
import com.example.actividad_2_ddapm.model.PostRequest
import com.example.actividad_2_ddapm.model.PostResponse
import okhttp3.Response

interface ApiService {
    @GET("campus.aspx")
    suspend fun getCampusNames(): CampusesResponse

    @POST("users.aspx/UserLogin")
    suspend fun postLoginRequests(@Body request: LoginRequest): LoginResponse

    @POST("users.aspx/UserUI")
    suspend fun postNewUserRequest(@Body request: NewUserRequest): NewUserResponse

    @POST("users.aspx/Friends")
    suspend fun postNewFriendsFilterRequest(@Body request: FriendsFilterRequest): FriendsFilterResponse

    @POST("users.aspx/FriendsUI")
    suspend fun postNewFriendsListRequest(@Body request: FriendsListRequest): FriendsListResponse

    @POST("posts.aspx/Posts")
    suspend fun newPostFilterRequest(@Body request: PostFilterRequest): PostFilterResponse

    @POST("posts.aspx/PostsUI")
    suspend fun newPostRequest(@Body request: PostRequest): PostResponse
}
