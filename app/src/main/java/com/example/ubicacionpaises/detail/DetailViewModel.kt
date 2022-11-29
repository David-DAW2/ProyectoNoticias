package com.example.ubicacionpaises.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ubicacionpaises.model.Artista

class DetailViewModel(artista: Artista): ViewModel() {
    private val _artista = MutableLiveData(artista)
    val artista: LiveData<Artista> get() = _artista
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val artista: Artista): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(artista) as T
    }

}