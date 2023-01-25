package com.example.rattan.model

import com.google.gson.annotations.SerializedName

class userModel (
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("first_name")
    val first_name: String? = null,
    @SerializedName("last_name")
    val last_name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("role")
    val role: String? = null,
    @SerializedName("gambar")
    val gambar: String? = null,
        )