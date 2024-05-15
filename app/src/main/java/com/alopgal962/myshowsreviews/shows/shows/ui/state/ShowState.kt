package com.alopgal962.myshowsreviews.shows.shows.ui.state

data class ShowState (
    var titulo:String? = "",
    var descripcion:String? ="",
    var imagen:String? ="",
    var puntuacion:String? ="",
    var votos:String? = "",
    var mipuntuacion:Int? = 0,
    var miresena:String? = ""
)