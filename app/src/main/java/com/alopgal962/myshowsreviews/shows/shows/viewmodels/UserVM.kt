package com.alopgal962.myshowsreviews.shows.shows.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.myshowsreviews.shows.shows.data.model.User
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserVM : ViewModel() {

    //Usuario

    private var _user = MutableStateFlow(User())

    var user = _user.asStateFlow()

    //Campos Inserccion y usuario
    var imagenRegister by mutableStateOf("")
    var nombreRegister by mutableStateOf("")
    var emaiLRegisterLogin by mutableStateOf("")
    var passwordRegisterLogin by mutableStateOf("")

    //Campos de usuario
    var listaAmigos:MutableList<Int>? by mutableStateOf(mutableListOf())
    var listaPeticiones:MutableList<Int>? by mutableStateOf(mutableListOf())
    var listaSeries:MutableList<ShowState>? by mutableStateOf(mutableListOf())

    var disponible by mutableStateOf(true)

    //Firebase autenticacion
    var VMFireAuth = Firebase.auth
    //Firebase base de datos
    var VMFireDB = Firebase.firestore

    fun registrarme(navegacion: () -> Unit) {
        viewModelScope.launch {
            try {
                if (imagenRegister != "" && nombreRegister != "" && emaiLRegisterLogin != "" && passwordRegisterLogin.length >= 6 && comprobacionNombre() == true) {
                    VMFireAuth.createUserWithEmailAndPassword(emaiLRegisterLogin, passwordRegisterLogin)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                VMFireDB.collection("Usuarios").document(emaiLRegisterLogin).set (
                                    hashMapOf(
                                        "imagen" to imagenRegister,
                                        "nombre" to nombreRegister,
                                        "email" to emaiLRegisterLogin,
                                        "contraseña" to passwordRegisterLogin,
                                        "listaAmigos" to listaAmigos,
                                        "listaPeti" to listaPeticiones,
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
                            _user.value.name = it.get("nombre").toString()
                            _user.value.image = it.get("imagen").toString()
                            _user.value.email = it.get("email").toString()
                            _user.value.listaAmigos = it.get("listaAmigos") as MutableList<Int>?
                            _user.value.listaSeries = it.get("listaSeries") as MutableList<ShowState>
                            _user.value.listaPeticiones = it.get("listaPeti") as MutableList<Int>
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


@SuppressLint("SuspiciousIndentation")
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

    fun añadidaALista(show:ShowState):Boolean{
        var result = false
        if (_user.value.listaSeries?.contains(show)==false){
            _user.value.listaSeries?.add(show)
            result=true
        }
        else{
            result=false
        }
        return result
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

}