package com.alopgal962.myshowsreviews.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class RegisterLoginVM:ViewModel() {

    var imagen by mutableStateOf("")
    var nombre by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var VMFIre = Firebase.auth
    var VMFireBase = Firebase.firestore

    fun registrarme(navegacion:() -> Unit){
        viewModelScope.launch {
            try {
                if (imagen!="" && nombre!="" && email!="" && password.length>6){
                    VMFIre.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            VMFireBase.collection("Usuarios").document(email).set(
                                hashMapOf(
                                    "imagen" to imagen,
                                    "nombre" to nombre,
                                    "email" to email,
                                    "contraseña" to password
                                ) )
                            navegacion()
                        }
                        }
                    }
            }
            catch (e:Exception){
                Log.d("ERRORREGISTER","No se pudo registrar")
            }
        }
    }

    fun iniciarsesion(navegacion: () -> Unit){
        viewModelScope.launch {
            try {
                if (email.isNotEmpty() && password.isNotEmpty()){
                    VMFIre.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            navegacion()
                        }
                        else Log.d("ERROR-SIGN","Error al iniciar sesion, email o contraseña incorrectos")
                    }
                }
            }catch (e:Exception){
                Log.d("ERROR-SIGN-v2","Error al intentar iniciar sesion")
            }
        }
    }

    fun borrarCampos(){
        imagen=""
        nombre=""
        email=""
        password=""
    }

}