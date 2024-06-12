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

    var listaSeries:MutableList<ShowState>? = null

    //Aqui se guardará la imagen que el usuario elige

    var image:String? = null
}