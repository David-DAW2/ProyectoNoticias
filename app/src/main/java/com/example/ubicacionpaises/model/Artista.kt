package com.example.ubicacionpaises.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artista(val nombre:String, val urlImagen:String) : Parcelable {
}