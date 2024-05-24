package com.alopgal962.myshowsreviews.shows.shows.data.model

import com.google.gson.annotations.SerializedName

data class ShowModel(
    @SerializedName("title")
    var titulo:String="",
    @SerializedName("overview")
    var descripcion:String="",
    @SerializedName("poster_path")
    var imagen:String="",
    @SerializedName("release_date")
    var fechasalida:String="",
    @SerializedName("vote_average")
    var puntuacion:String,
    @SerializedName("vote_count")
    var votos:String
)
