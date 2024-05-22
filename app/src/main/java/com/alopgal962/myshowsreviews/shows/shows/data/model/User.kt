package com.alopgal962.myshowsreviews.shows.shows.data.model

import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState

class User {

    //Por este id se identificará a cada usuario, es unico por cada usuario no se puede repetir
    //var id:String?=null
    //Nombre del usuario

    var nombre:String?=null

    //Email

    var email:String?=null

    //Contraseña

    var password:String?=null

    //Aqui se guardarán la lista de amigos, pero en vez de guardar user, se guarda el codigo por el que se identifican

    var listaAmigos:MutableList<User>?=null

    //Igualmente, se guardará las peticiones de alguien que quiere ser amigo del usuario en una lista de peticiones de usuario.

    var listaPeticiones:MutableList<User>?=null

    //Ahora se guardará la imagen que el usuario elige

    var listaSeries:MutableList<ShowState>? = null

    var image:String? = null;


    fun showUserInfo(user:User):String{
        return "name -> ${user.nombre} email -> ${user.email} pass ${user.password} image ${user.image} listamigos ${user.listaAmigos} series ${user.listaSeries} listapeti ${user.listaPeticiones} "
    }
}