package com.alopgal962.myshowsreviews.shows.shows.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.myshowsreviews.shows.shows.data.model.User
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@SuppressLint("MutableCollectionMutableState")
class UserVM : ViewModel() {

    //Usuario
    private var _user = MutableStateFlow(User())

    //Usuario a mostrar en la UI
    var user = _user.asStateFlow()

    //Guarda el valor del show que se va a insertar
    private var _showInsertar = MutableStateFlow(ShowState())

    //Valor que se utilizará en la UI
    var showInsertar = _showInsertar.asStateFlow()

    //Guarda el valor del show que se quiere ver en la pantalla
    private var _showEstadisticas = MutableStateFlow(ShowState())

    //Show de estadisticas en la UI
    var showEstadisticas = _showEstadisticas.asStateFlow()

    //Campos Inserccion y usuario
    var imagenRegister by mutableStateOf("")
    var nombreRegister by mutableStateOf("")
    var emaiLRegisterLogin by mutableStateOf("")
    var passwordRegisterLogin by mutableStateOf("")

    //Campos de usuario
    var listaSeries: MutableList<ShowState> = mutableListOf()

    var listanombres = mutableListOf<String>()

    //Valor que guarda el valor de reseña
    var resena by mutableStateOf("")
    //Valor que guarda el valor de la puntuacion
    var puntuacion by mutableStateOf("")

    //Valor que guarda la puntuacion media
    var puntuacionmedia by mutableStateOf("")

    //Lista de usuarios
    var listaUsuarios by mutableStateOf(mutableListOf<User>())

    //Firebase autenticacion
    var VMFireAuth = Firebase.auth

    //Firebase base de datos
    var VMFireDB = Firebase.firestore

    fun registrarme(navegacion: () -> Unit) {
        viewModelScope.launch {
            try {
                obtenerNombres()
                if (imagenRegister != "" && nombreRegister != "" && emaiLRegisterLogin != "" && passwordRegisterLogin.length >= 6 && !listanombres.contains(nombreRegister)) {
                    VMFireAuth.createUserWithEmailAndPassword(
                        emaiLRegisterLogin,
                        passwordRegisterLogin
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).set(
                                hashMapOf(
                                    "image" to imagenRegister,
                                    "nombre" to nombreRegister,
                                    "email" to emaiLRegisterLogin,
                                    "password" to passwordRegisterLogin,
                                    "listaSeries" to listaSeries,
                                )
                            ).addOnCompleteListener {
                                navegacion()
                                borrarCampos()
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("ERRORREGISTER", "No se pudo registrar")
            }
        }
    }

    fun iniciarsesion(navegacion: () -> Unit) {
        viewModelScope.launch {
            try {
                if (emaiLRegisterLogin.isNotEmpty() && passwordRegisterLogin.isNotEmpty()) {
                    VMFireAuth.signInWithEmailAndPassword(emaiLRegisterLogin, passwordRegisterLogin)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).get()
                                    .addOnSuccessListener {
                                        _user.value = it.toObject<User>()!!
                                        navegacion()
                                    }.addOnFailureListener {
                                    Log.d(
                                        "ERROR-DatosUsuario",
                                        "Error al obtener los datos de usuario"
                                    )
                                }

                            } else Log.d(
                                "ERROR-SIGN",
                                "Error al iniciar sesion, email o contraseña incorrectos"
                            )
                        }
                }
            } catch (e: Exception) {
                Log.d("ERROR-SIGN-v2", "Error al intentar iniciar sesion")
            }
        }
    }

    fun obtenerListaUsuarios() {
        viewModelScope.launch {
            var listatemporal = mutableListOf<User>()
            try {
                VMFireDB.collection("Usuarios").get().addOnSuccessListener {
                    var listaUsuariosSinConvertir = it.toObjects<User>()
                    for (usuario in listaUsuariosSinConvertir) {
                        listatemporal.add(usuario)
                    }
                    listaUsuarios = listatemporal.shuffled().toMutableList()
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    fun comprobacionSerie(show: ShowState, onadding: () -> Unit) {
        viewModelScope.launch {
            try {
                if (_user.value.listaSeries!!.contains(show) == true) {
                    Log.d("ERROR-INSERCCION-SHOW", "El show a introducir ya existe")
                } else {
                    _showInsertar.value = show
                    onadding()
                }
            } catch (e: Exception) {
                Log.d("ERROR-Serie-insert", "Error al insertar la serie")
            }
        }
    }


    fun anadirSerieDB(navegacion: () -> Unit) {
        viewModelScope.launch {
            try {
                if (resena != "" && puntuacion.toInt() > 0 && puntuacion.toInt() <= 10) {
                    //Se establece a la serie que se quiere insertar la puntuacion asignada
                    var show = _showInsertar.value.copy()
                    show.mipuntuacion = puntuacion
                    show.miresena = resena
                    _user.value.listaSeries?.add(show)
                    //Se añade a la lista local la serie de usuarios
                    Log.d(
                        "especificaciones",
                        _showInsertar.value.puntuacion.toString() + showInsertar.value.titulo.toString()
                    )
                    //Se restablecen los valores
                    VMFireDB.collection("Usuarios")
                        .document(VMFireAuth.currentUser?.email.toString())
                        .update("listaSeries", _user.value.listaSeries).addOnSuccessListener {
                        navegacion()
                        ResetSerieInsert()
                        recuperarSeriesUsuario()
                        Log.d("inserccioncorrecta", "serie insertada")
                    }
                } else {
                    Log.d("ERROR-INSERCCION-SERIE", "La reseña/puntuacion no pueden ser nul@s!")
                }
            } catch (e: Exception) {
                Log.d("ERROR-TRY-INSERCCION-SERIE", "Error al intentar añadir la serie")
            }
        }
    }

    fun seeFullyShowDetails(show:ShowState){
        viewModelScope.launch {
            try {
                _showEstadisticas.value = show
            }
            catch (e:Exception){
                Log.d("ERROR-ESTADISICAS-SERIE","Excepcion al")
            }
        }
    }


    fun checkShowRate(show: ShowState): String {
        var puntuacion = ""
        if (show.puntuacion!!.length >= 4) {
            puntuacion = show.puntuacion!!.substring(0, 3)
        } else {
            puntuacion = show.puntuacion.toString()
        }
        return puntuacion
    }

    fun ResetSerieInsert() {
        resena = ""
        puntuacion = ""
        _showInsertar.value = ShowState()
    }

    fun ResetSerieValues() {
        resena = ""
        puntuacion = ""
    }

    fun setShortResena(resena:String):String{
        var resenaModified=resena
        if (resena.length>40){
            resenaModified = resena.substring(0,40) + "..."
        }
        return resenaModified
    }

    fun recuperarSeriesUsuario() {
        viewModelScope.launch {
            try {
                var listatemporal = mutableListOf<ShowState>()
                if (emaiLRegisterLogin.isNotEmpty()) {
                    var documento =
                        VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).get().await()
                    var lista = documento as MutableList<ShowState>
                    for (show in lista) {
                        listatemporal.add(show)
                    }
                    _user.value.listaSeries = listatemporal
                }
            } catch (e: Exception) {
                Log.d("ERROR-RECUPERAR", "Error al recuperar las series")
            }
        }
    }

    /*
    Borrar de la lista la pelicula deseada, posteriormente guarda los cambios en la base de datos
     */
    fun deletePelicula(show: ShowState) {
        viewModelScope.launch {
            try {
                if (_user.value.listaSeries!!.contains(show)) {
                    _user.value.listaSeries!!.remove(show)
                    VMFireDB.collection("Usuarios")
                        .document(VMFireAuth.currentUser?.email.toString())
                        .update("listaSeries", _user.value.listaSeries)
                } else {
                    Log.d("ERROR-Eliminacion", "Error al eliminar show")
                }
            } catch (e: Exception) {
                Log.d("EXCEPCION-DELETE", "Excepcion durante eliminacion de show")
            }
        }
    }

    /*
    Elimina todas las series del usuario de la base de datos
     */
    fun deleteAllShows() {
        viewModelScope.launch {
            try {
                _user.value.listaSeries?.clear()
                VMFireDB.collection("Usuarios").document(VMFireAuth.currentUser?.email.toString())
                    .update("listaSeries", _user.value.listaSeries)
            } catch (e: Exception) {
                Log.d("ERROR-ELIMINAR-COMPLETO", "Excepcion al intentar eliminar todas las seriess")
            }
        }
    }


    //Cierra sesion, el usuario es nulo
    fun cerrarSesion(navegacion: () -> Unit) {
        try {
            navegacion()
            _user.value = User()
        } catch (e: Exception) {
            Log.d("ERROR-CERRAR", "Error al intentar cerrar sesion")
        }
    }

    //Borra campos
    fun borrarCampos() {
        imagenRegister = ""
        nombreRegister = ""
        emaiLRegisterLogin = ""
        passwordRegisterLogin = ""
    }

    fun setShortTitle(show: ShowState): String {
        var title = ""
        if (show.titulo!!.length >= 25) {
            title = show.titulo!!.substring(0, 25) + "..."
        } else
            title = show.titulo!!
        return title
    }


    fun obtenerNombres(){
        viewModelScope.launch {
            try {
                var listatemporal = mutableListOf<String>()
                VMFireDB.collection("Usuarios").get().addOnSuccessListener {
                    for (user in it){
                        listatemporal.add(user.get("nombre").toString())
                    }
                    listanombres=listatemporal
                    Log.d("lista",listanombres.toString())
                }
            }
            catch (e:Exception){
                Log.d("exceptioname","nombre no dispo")
            }
        }
    }

}