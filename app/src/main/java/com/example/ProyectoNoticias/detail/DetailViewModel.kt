package com.example.ProyectoNoticias.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ProyectoNoticias.model.Noticia

class DetailViewModel(noticia: Noticia): ViewModel() {
    private val _artista = MutableLiveData(noticia)
    val noticia: LiveData<Noticia> get() = _artista
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val noticia: Noticia): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(noticia) as T
    }

}