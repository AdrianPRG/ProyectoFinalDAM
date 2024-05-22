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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
class UserVM : ViewModel() {

    //Usuario
    private var _user = MutableStateFlow(User())

    //Usuario a mostrar en la UI
    var user = _user.asStateFlow()

    //Campos Inserccion y usuario
    var imagenRegister by mutableStateOf("")
    var nombreRegister by mutableStateOf("")
    var emaiLRegisterLogin by mutableStateOf("")
    var passwordRegisterLogin by mutableStateOf("")

    //Campos de usuario
    var listaAmigos:MutableList<User>? by mutableStateOf(mutableListOf())
    var listaPeticiones:MutableList<User>? by mutableStateOf(mutableListOf())
    var listaSeries:MutableList<ShowState>? by mutableStateOf(mutableListOf())

    var disponible by mutableStateOf(true)

    //Firebase autenticacion
    var VMFireAuth = Firebase.auth
    //Firebase base de datos
    var VMFireDB = Firebase.firestore

    var amigo = mutableStateOf("")

    var petificiones by mutableStateOf(0)

    fun registrarme(navegacion: () -> Unit) {
        viewModelScope.launch {
            try {
                if (imagenRegister != "" && nombreRegister != "" && emaiLRegisterLogin != "" && passwordRegisterLogin.length >= 6 && comprobacionNombre() == true) {
                    VMFireAuth.createUserWithEmailAndPassword(emaiLRegisterLogin, passwordRegisterLogin)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).set (
                                    hashMapOf(
                                        "image" to imagenRegister,
                                        "nombre" to nombreRegister,
                                        "email" to emaiLRegisterLogin,
                                        "password" to passwordRegisterLogin,
                                        "listaAmigos" to listaAmigos,
                                        "listaPeticiones" to listaPeticiones,
                                        "listaSeries" to listaSeries
                                    )
                                ).addOnCompleteListener{
                                    navegacion()
                                    borrarCampos()
                                }
                            }
                        }
                }
        }
        catch(e:Exception) {
            Log.d("ERRORREGISTER", "No se pudo registrar")
        }
    }
}

fun iniciarsesion(navegacion: () -> Unit) {
    viewModelScope.launch {
        try {
            if (emaiLRegisterLogin.isNotEmpty() && passwordRegisterLogin.isNotEmpty()) {
                VMFireAuth.signInWithEmailAndPassword(emaiLRegisterLogin, passwordRegisterLogin).addOnCompleteListener {
                    if (it.isSuccessful) {
                        VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).get().addOnSuccessListener {
//                            _user.value.nombre = it.get("nombre").toString()
//                            _user.value.image = it.get("image").toString()
//                            _user.value.email = it.get("email").toString()
//                            _user.value.password = it.get("password").toString()
//                            _user.value.listaAmigos = it.get("listaAmigos") as MutableList<String>?
//                            _user.value.listaSeries = it.get("listaSeries") as MutableList<ShowState>
//                            _user.value.listaPeticiones = it.get("listaPeticiones") as MutableList<String>
                            _user.value = it.toObject<User>()!!
                            navegacion()
                        }.addOnFailureListener {
                            Log.d("ERROR-DatosUsuario","Error al obtener los datos de usuario")
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

    fun recuperarSeriesUsuario(){
        viewModelScope.launch {
            try {
                if (emaiLRegisterLogin.isNotEmpty()){
                    VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).get().addOnSuccessListener {
                        _user.value.listaSeries = it.get("listaSeries") as MutableList<ShowState>?
                    }.addOnFailureListener {
                        Log.d("errorserie","Error al obtener las series de usuario")
                    }
                }
            }catch (e:Exception){
                Log.d("ERROR-RECUPERAR","Error al recuperar las series")
            }
        }
    }


fun meterSeriesUsuario(show:ShowState){
    viewModelScope.launch {
        try {
            if (_user.value.listaSeries!!.filter{it -> it.titulo==show.titulo}.count()>=1){
            }
            else{
                _user.value.listaSeries!!.add(show)
                VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).update("listaSeries",_user.value.listaSeries)
            }
        }catch (e:Exception){
            Log.d("ERROR-Serie-insert","Error al insertar la serie")
        }
    }
}

fun mandarSolicitud(email:String){
    viewModelScope.launch {
        try {
            var UserSoliSended = User()
            VMFireDB.collection("Usuarios").document(email).get().addOnSuccessListener { it ->
                if (it.toObject<User>()==null || it.toObject<User>()?.email == VMFireAuth.currentUser?.email.toString() || it.toObject<User>()?.listaPeticiones!!.contains(setPrivateFields())){
                    Log.d("USUARIO-NULO","El usuario que se introdujo es nulo o no se puede enviar invitacion de amistad a uno mismo, o quizas ya le ha enviado una peticion de amistad anteriormente")
                    amigo.value=""
                }
                else{
                    UserSoliSended = it.toObject<User>()!!
                    UserSoliSended.nombre = it.get("nombre").toString()
                    UserSoliSended.listaPeticiones?.add(setPrivateFields())
                    VMFireDB.collection("Usuarios").document(email).update("listaPeticiones",UserSoliSended.listaPeticiones).addOnCompleteListener{
                    amigo.value=""
                    Log.d("PETICION-ENVIADA",UserSoliSended.listaPeticiones.toString())
                    }
                }
            }
        }catch (e:Exception){

        }
    }
}

fun ObtenerSolicitudesAmistad(){
    viewModelScope.launch {
        try {
            VMFireDB.collection("Usuarios").document(VMFireAuth.currentUser!!.email.toString()).get().addOnSuccessListener {
                _user.value.listaPeticiones = it.get("listaPeticiones") as MutableList<User>
            }
        }catch (e:Exception){
        }
    }
}

fun cerrarSesion(navegacion: () -> Unit){
    navegacion()
    _user.value = User()
}

fun borrarCampos() {
    imagenRegister = ""
    nombreRegister = ""
    emaiLRegisterLogin = ""
    passwordRegisterLogin = ""
}


    fun comprobacionNombre(): Boolean {
        var disponible = true
        try {
            VMFireDB.collection("Usuarios").whereEqualTo("nombre", nombreRegister).get()
                .addOnSuccessListener { datos ->
                    for (doc in datos) {
                        if (doc.getString("nombre") == nombreRegister) {
                            disponible = false
                        }
                    }
                }.addOnFailureListener {
                    Log.d("ErrorAccesoNombre", "Error al acceder a la base de datos")
                }
        } catch (e: Exception) {
            Log.d("ErrorComprobacion", "Error al comprobar nombre de usuario")
        }
        return disponible
    }

    fun setPrivateFields():User{
        var user = User()
        user = _user.value
        user.password=null
        user.listaAmigos=null
        user.listaAmigos=null
        return user
    }

}