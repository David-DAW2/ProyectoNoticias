@file:Suppress("UNCHECKED_CAST")

package com.example.ProyectoNoticias

import androidx.lifecycle.*
import com.example.ProyectoNoticias.model.NewsClient
import com.example.ProyectoNoticias.model.Noticia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(apiKey: String) : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state
    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val result =  NewsClient.service.lasNewsList(apiKey)
            val noticias = result.articles.map {
                Noticia(
                    it.title,
                    it.urlToImage,
                    it.description,
                    it.author,


                )
            }
            _state.value = _state.value?.copy(loading = false, noticias = noticias)

        }
    }

    fun navigateTo(noticia: Noticia) {
        _state.value = _state.value?.copy(navigateTo = noticia)
    }

    fun onNavigateDone(){
        _state.value = _state.value?.copy(navigateTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val noticias: List<Noticia>? = null,
        val navigateTo: Noticia? = null
    )

}
class MainViewModelFactory(private val apiKey: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(apiKey) as T
    }
}