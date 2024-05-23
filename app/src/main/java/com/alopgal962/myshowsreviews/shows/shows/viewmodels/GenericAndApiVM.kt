package com.alopgal962.myshowsreviews.shows.shows.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.myshowsreviews.shows.shows.data.ShowRepository
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @property numpage Es un contador, que cada vez que pulsemos, se incrementará o disminuirá, servirá para ir a la siguiente pagina o a la anterior
 * @property ShowsRepository Es una instancia de la clase ShowRepository, la cual contiene las funciones de conversion y de llamamiento a la api, por medio de las funciones de GetapiService
 * @property _listaShow Es una lista que almacena los resultados de la respues de la api. Obtiene los datos de la api mediante ShowsRepository
 * @property listashow Es la lista que se mostrará en la UI. Almacena los datos de _listashow
 * @property _show Es el show en el que se almacenará el show cuando pulsemos para ver informacion sobre el.
 * @property show Es el show que se mostrará en la UI. Almacena el valor de _show
 */
class GenericAndApiVM:ViewModel() {


    //Numero de pagina que ira incrementandose
    var numpage = 1

    //Instanciamos el repositorio, donde tenemos almacenadas las funciones de conversion y recuperacion de datos
    val ShowsRepository = ShowRepository()

    //Declaramos la variable _listashow que sera privada
    //en esta haremos los cambios, es un estado y se va viendo constantemente
    var _listaShow = MutableStateFlow<List<ShowState>>(emptyList())

    //La lista publica que mostraremos
    val listashow = _listaShow.asStateFlow()

    //Show que se guardará el show cuando se haga click encima de boton informacion del correspondiente
    private var _show = MutableStateFlow(ShowState())

    //Variable que almacena false, para que ponga el boton de refresh deshabilitado durnte 5 segundos
    var disabled = MutableStateFlow(true)

    //Show que se mostrará
    val show = _show.asStateFlow()

    //Inicializamos la funciond de obtener peliculas
    init {
        obtenerPeliculas(numpage)
    }

    /*
        Llama a la funcion GetShows de el repositorio, y almacena los resultados en la lista _listashow.
     */

    fun obtenerPeliculas(numpagina:Int) {
        try {
            viewModelScope.launch {
                _listaShow.value = ShowsRepository.GetShows(numpagina).resultados
            }
        } catch (e: Exception) {
            Log.d("ErrorObtener", "Error al llamar funcion de obtener peliculas")
        }
    }

    /**
     * @param nombre es el nombre que se le pasará a la funcion GetInfoShow, para que busque en la base de datos la pelicula que contenga ese nombre
     */

    fun obtenerPelicula(nombre: String) {
        try {
            viewModelScope.launch {
                _show.value = _listaShow.value.find { it.titulo == nombre }!!
            }
        } catch (e: Exception) {
            Log.d("ErrorSpecificShow", "Error al obtener el show especifico")
        }
    }


    /*
       La funcion refresh avanza o retrocede en las paginas
     */
    fun refresh(go: Boolean) {
        viewModelScope.launch {
            if (go == true) {
               if (numpage==499){
                   numpage=1
               }
                else{
                    numpage+=1
               }
            }
            else if (go == false) {
                if (numpage == 1) {
                    numpage = 499
                }
                else{
                    numpage-=1
                }
            }
            obtenerPeliculas(numpage)
            disabled.value = false
            delay(2000)
            disabled.value = true
        }
    }
}