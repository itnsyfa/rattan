package com.example.rattan.service_api

import com.example.rattan.model.SignInBody
import retrofit2.Call
import retrofit2.http.GET
import com.example.rattan.model.barangModel
import com.example.rattan.model.userModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @GET("/api/posts")
    fun getPost(): Call<List<barangModel>>

    @GET("/api/termurah")
    fun getMurah(): Call<List<barangModel>>

    @GET("/api/terlaris")
    fun getLaris(): Call<List<barangModel>>

    @GET("/api/users")
    fun getUsers(): Call<List<userModel>>

    @Headers("Content-Type:application/json")
    @POST("/api/login")
    fun postLogin(@Body body: SignInBody): Call<SignInBody>
}