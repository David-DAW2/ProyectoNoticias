package com.example.ubicacionpaises

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ubicacionpaises.model.Artista
import com.example.ubicacionpaises.model.ArtistaProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(): ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val artistas =  withContext(Dispatchers.IO){ArtistaProvider.getArtistas()}
            _state.value = _state.value?.copy(loading = false, artistas = artistas)
        }
    }

    fun navigateTo(artista: Artista) {
        _state.value = _state.value?.copy(navigateTo = artista)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val artistas: List<Artista>? = null,
        val navigateTo: Artista? = null
    )

}