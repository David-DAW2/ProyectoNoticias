package com.example.ProyectoNoticias.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Noticia(val nombre:String, val urlImagen:String?, val contenido:String?, val autor:String?) : Parcelable {
}