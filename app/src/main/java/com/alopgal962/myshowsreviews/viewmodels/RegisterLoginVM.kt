package com.alopgal962.myshowsreviews.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.getField
import kotlinx.coroutines.launch

class RegisterLoginVM : ViewModel() {

    var imagen by mutableStateOf("")
    var nombre by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var disponible by mutableStateOf(true)

    var VMFireAuth = Firebase.auth
    var VMFireDB = Firebase.firestore

    fun registrarme(navegacion: () -> Unit) {
        viewModelScope.launch {
            try {
                if (imagen != "" && nombre != "" && email != "" && password.length >= 6 && comprobacionNombre() == true) {
                    VMFireAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                VMFireDB.collection("Usuarios").document(email).set (
                                    hashMapOf(
                                        "imagen" to imagen,
                                        "nombre" to nombre,
                                        "email" to email,
                                        "contraseña" to password
                                    )
                                ).addOnCompleteListener{
                                    navegacion()
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
            if (email.isNotEmpty() && password.isNotEmpty()) {
                VMFireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        navegacion()
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
            VMFireDB.collection("Usuarios").whereEqualTo("nombre", nombre).get()
                .addOnSuccessListener { datos ->
                    for (doc in datos) {
                        if (doc.getString("nombre") == nombre) {
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

fun borrarCampos() {
    imagen = ""
    nombre = ""
    email = ""
    password = ""
}

}