package com.alopgal962.myshowsreviews.shows.shows.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.myshowsreviews.shows.shows.data.model.RestrictedUser
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
import java.util.UUID

@SuppressLint("MutableCollectionMutableState")
class UserVM : ViewModel() {

    //Usuario
    private var _user = MutableStateFlow(User())

    //Usuario a mostrar en la UI
    var user = _user.asStateFlow()

    private var _showInsertar = MutableStateFlow(ShowState())

    var showInsertar = _showInsertar.asStateFlow()

    var myrestricteduser = MutableStateFlow(RestrictedUser())

    //Campos Inserccion y usuario
    var imagenRegister by mutableStateOf("")
    var nombreRegister by mutableStateOf("")
    var emaiLRegisterLogin by mutableStateOf("")
    var passwordRegisterLogin by mutableStateOf("")
    var identificador by mutableStateOf("")

    //Campos de usuario
    var listaAmigos: MutableList<RestrictedUser> = mutableListOf()
    var listaPeticiones: MutableList<RestrictedUser> = mutableListOf()
    var listaSeries: MutableList<ShowState> = mutableListOf()

    var resena by mutableStateOf("")
    var puntuacion by mutableStateOf("")

    var listaUsuarios by mutableStateOf(mutableListOf<User>())

    //Firebase autenticacion
    var VMFireAuth = Firebase.auth

    //Firebase base de datos
    var VMFireDB = Firebase.firestore

    //Email del amigo a enviar solicitud
    var emailFriend = mutableStateOf("")

    fun registrarme(navegacion: () -> Unit) {
        viewModelScope.launch {
            try {
                if (imagenRegister != "" && nombreRegister != "" && emaiLRegisterLogin != "" && passwordRegisterLogin.length >= 6 ) {
                    VMFireAuth.createUserWithEmailAndPassword(emaiLRegisterLogin, passwordRegisterLogin).addOnCompleteListener {
                            if (it.isSuccessful) {
                                generateId()
                                setID()
                                VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).set(
                                    hashMapOf(
                                        "id" to identificador,
                                        "image" to imagenRegister,
                                        "nombre" to nombreRegister,
                                        "email" to emaiLRegisterLogin,
                                        "password" to passwordRegisterLogin,
                                        "listaAmigos" to listaAmigos,
                                        "listaPeticiones" to listaPeticiones,
                                        "listaSeries" to listaSeries,
                                    )
                                ).addOnCompleteListener {
                                    VMFireDB.collection("Identificadores").document(identificador + nombreRegister).set(
                                        hashMapOf(
                                            "id" to generateId()
                                        )
                                    ).addOnCompleteListener{
                                        navegacion()
                                        borrarCampos()
                                    }
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
                                VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).get().addOnSuccessListener {
                                        _user.value = it.toObject<User>()!!
                                        obtainMyRestrictedUser(VMFireAuth.currentUser?.email.toString(), myrestricteduser.value)
                                        navegacion()
                                    }.addOnFailureListener {
                                    Log.d("ERROR-DatosUsuario", "Error al obtener los datos de usuario")
                                }

                            } else Log.d("ERROR-SIGN", "Error al iniciar sesion, email o contraseña incorrectos")
                        }
                }
            } catch (e: Exception) {
                Log.d("ERROR-SIGN-v2", "Error al intentar iniciar sesion")
            }
        }
    }
 fun obtenerListaUsuarios(){
     viewModelScope.launch {
         var listatemporal = mutableListOf<User>()
         try {
             VMFireDB.collection("Usuarios").get().addOnSuccessListener {
                 var listaUsuariosSinConvertir = it.toObjects<User>()
                 for (usuario in listaUsuariosSinConvertir){
                     listatemporal.add(usuario)
                 }
                 listaUsuarios = listatemporal.shuffled().toMutableList()
             }
         }catch (e:Exception){
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



    fun añadirSerieDB(navegacion: () -> Unit) {
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
                    VMFireDB.collection("Usuarios").document(VMFireAuth.currentUser?.email.toString()).update("listaSeries", _user.value.listaSeries).addOnSuccessListener {
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

    fun recuperarSeriesUsuario() {
        viewModelScope.launch {
            try {
                var listatemporal = mutableListOf<ShowState>()
                if (emaiLRegisterLogin.isNotEmpty()) {
                    var documento = VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).get().await()
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

    /*Saca de la base de datos un usuario, sin embargo, los campos peticiones y password seran nulos, ya que
      El usuario que se guarda en la lista de peticiones de otro usuario no debe de poseer estos campos
     */
    fun obtainMyRestrictedUser(email: String, restrictedUser: RestrictedUser) {
        viewModelScope.launch {
            try {
                VMFireDB.collection("Usuarios").document(email).get().addOnSuccessListener {
                    //Obtenemos solo los datos necesarios, la password no, ya que es algo privado del usuario, y la lista de peticiones igual
                    restrictedUser.image = it.get("image").toString()
                    restrictedUser.email = it.get("email").toString()
                    restrictedUser.nombre = it.get("nombre").toString()
                    restrictedUser.listaSeries = it.get("listaSeries") as MutableList<ShowState>
                }
            } catch (e: Exception) {
                Log.d(
                    "ERROR-RESTRICTED-USER",
                    "Error al obtener los datos del usuario (restringido)"
                )
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

    fun setID(){
        if (generateId().toInt()==0){
            identificador = "0" + UUID.randomUUID().toString().substring(0,2)
        }
        else{
            identificador = generateId().toString() + UUID.randomUUID().toString().substring(0,2)
        }
    }

    fun generateId():Int{
        var cont=0
        viewModelScope.launch {
            VMFireDB.collection("Identificadores").get().addOnSuccessListener {
                for (doc in it){
                    cont+=1
                }
            }
        }
        return cont
    }

}