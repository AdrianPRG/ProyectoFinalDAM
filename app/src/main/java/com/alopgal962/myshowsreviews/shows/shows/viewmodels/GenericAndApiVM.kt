package com.alopgal962.myshowsreviews.shows.shows.viewmodels


import android.net.http.HttpException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.myshowsreviews.shows.shows.data.ShowRepository
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * @property estado Guarda el estado de true o false, dependiendo de si hay conexion a internet o no.
 * @property numpage Es un contador, que cada vez que pulsemos, se incrementará o disminuirá, servirá para ir a la siguiente pagina o a la anterior
 * @property ShowsRepository Es una instancia de la clase ShowRepository, la cual contiene las funciones de conversion y de llamamiento a la api, por medio de las funciones de GetapiService
 * @property _listaShow Es una lista que almacena los resultados de la respues de la api. Obtiene los datos de la api mediante ShowsRepository
 * @property listashow Es la lista que se mostrará en la UI. Almacena los datos de _listashow
 * @property _show Es el show en el que se almacenará el show cuando pulsemos para ver informacion sobre el.
 * @property show Es el show que se mostrará en la UI. Almacena el valor de _show
 * @property disabled Booleano que actuará para que el boton de avanzar y retroceder se deshabilite por segundos
 */
class GenericAndApiVM:ViewModel() {

    var estado = MutableStateFlow(true)

    var numpage = getNumpageRandom()

    val ShowsRepository = ShowRepository()

    var _listaShow = MutableStateFlow<List<ShowState>>(emptyList())

    val listashow = _listaShow.asStateFlow()

    private var _show = MutableStateFlow(ShowState())

    val show = _show.asStateFlow()

    var disabled = MutableStateFlow(true)


    //Inicializamos la funcion de obtener peliculas

    /**
     * Llama a la funcion GetShows de el repositorio, y almacena los resultados en la lista _listashow.
     * Esta lista se mostrará en la UI
     * @param numpagina es el numero de pagina de busqueda en la api, ya que hay varias paginas con resultados
     * */

    fun obtenerPeliculas(numpagina:Int) {
        viewModelScope.launch {
            try {
                _listaShow.value = ShowsRepository.GetShows(numpagina).resultados
                estado.value=true
            } catch (e: HttpException) {
                estado.value=false
            }
            catch (e:IOException){
                estado.value=false
            }
        }
    }

    /**
     * Funcion que recibe un nombre y busca en la lista de shows el nombre pasado por parametros
     * @param nombre es el nombre que se pasará para que busque en la lista la pelicula que contenga ese nombre
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


    /**
     * La funcion refresh avanza o retrocede en las paginas
     * Una vez se ha sumado o restado el numero de pagina, se llama de nuevo a la funcion de obtener shows, ademas de que la propiedad
     * disabled cambia de estado durante 2 segundos, para inhabilitar el boton de avanzar o retroceder
     * @param go es booleano que, si es true, avanzará el numero de pagina, si no, retrocede
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
            else if (go==false) {
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

    /**
     * Devuelve un numero random que se asignará a la pagina
     * @return numero random
     */
    fun getNumpageRandom():Int{
         val num = (1..499).random()
        return num
    }
}