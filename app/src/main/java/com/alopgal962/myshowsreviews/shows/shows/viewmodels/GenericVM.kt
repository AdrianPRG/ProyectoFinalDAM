package com.alopgal962.myshowsreviews.shows.shows.viewmodels


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.myshowsreviews.shows.shows.data.ShowRepository
import com.alopgal962.myshowsreviews.shows.shows.data.retrofit.GetApi.GetApiService
import com.alopgal962.myshowsreviews.shows.shows.data.retrofit.Retrofit
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GenericVM:ViewModel() {

    val ShowsRepository = ShowRepository()

    var listaShow = MutableStateFlow<List<ShowState>>(emptyList())

    init {
        obtenerPeliculas()
    }
    fun obtenerPeliculas(){
        try {
            viewModelScope.launch {
                listaShow.value = ShowsRepository.GetShows().resultados
                Log.d("LISTAA",listaShow.value.first().toString())
            }
        }
        catch (e:Exception){
            Log.d("ErrorObtener","Error al llamar funcion de obtener peliculas")
        }
        }
}