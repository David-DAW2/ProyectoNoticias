package com.example.ubicacionpaises

import androidx.lifecycle.*
import com.example.ubicacionpaises.model.Artista
import com.example.ubicacionpaises.model.NewsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(apiKey: String) : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state
    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val result =  NewsClient.service.lasNewsList(apiKey)
            val artistas = result.articles.map {
                Artista(
                    it.title,
                    it.urlToImage,
                    it.description,
                    it.author,


                )
            }
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
class MainViewModelFactory(private val apiKey: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(apiKey) as T
    }
}