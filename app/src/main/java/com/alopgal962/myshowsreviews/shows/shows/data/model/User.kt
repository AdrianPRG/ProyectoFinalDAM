package com.alopgal962.myshowsreviews.shows.shows.data.model

class User {

    //Por este id se identificará a cada usuario, es unico por cada usuario no se puede repetir
    var id:String?=null
    //Nombre del usuario
    var name:String?=null
    //Email
    var email:String?=null
    //Contraseña
    var password:String?=null
    //Aqui se guardarán la lista de amigos, pero en vez de guardar user, se guarda el codigo por el que se identifican
    var listaAmigos:MutableList<Int>?=null
    //Igualmente, se guardará las peticiones de alguien que quiere ser amigo del usuario en una lista de peticiones de usuario.
    var listaPeticiones:MutableList<Int>?=null
    //Ahora se guardará la imagen que el usuario elige
    var image:String="";
}