package com.reggya.biography.data.network

import com.reggya.biography.data.model.UsersResponseItem
import retrofit2.http.GET

interface ApiService {

    @GET("getData/test")
    suspend fun getUsers(): List<UsersResponseItem?>
    
}