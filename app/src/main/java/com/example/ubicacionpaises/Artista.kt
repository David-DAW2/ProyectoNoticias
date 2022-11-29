package com.example.ubicacionpaises

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artista(val nombre:String, val urlImagen:String) : Parcelable {
}