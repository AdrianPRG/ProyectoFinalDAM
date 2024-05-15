package com.alopgal962.myshowsreviews.shows.shows.viewmodels


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.myshowsreviews.shows.shows.data.ShowRepository
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GenericVM:ViewModel() {

    var numpage = 1
    //Instanciamos el repositorio, donde tenemos almacenadas las funciones de conversion y recuperacion de datos
    val ShowsRepository = ShowRepository()
    //Declaramos la variable _listashow que sera privada
    //en esta haremos los cambios, es un estado y se va viendo constantemente
    private var _listaShow = MutableStateFlow<List<ShowState>>(emptyList())

    //La lista publica que mostraremos
    val listashow = _listaShow.asStateFlow()

    init {
        obtenerPeliculas()
    }
    fun obtenerPeliculas(){
        try {
            viewModelScope.launch {
                _listaShow.value = ShowsRepository.GetShows(numpage).resultados
            }
        }
        catch (e:Exception){
            Log.d("ErrorObtener","Error al llamar funcion de obtener peliculas")
        }
        }

    fun refresh(){
        if (numpage<=499){
            numpage+=1
        }
        else{
            numpage=1
        }
    }
}