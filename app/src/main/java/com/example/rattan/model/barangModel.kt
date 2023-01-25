package com.example.rattan.model

import com.google.gson.annotations.SerializedName

data class barangModel (

    @SerializedName("barang_id")
    val barang_id: Int? = null,
    @SerializedName("nama")
    val nama: String? = null,
    @SerializedName("katagori")
    val katagori: String? = null,
    @SerializedName("harga")
    val harga: String? = null,
    @SerializedName("stok")
    val stok: String? = null,
    @SerializedName("gambar")
    val gambar: String? = null,

)