package com.alopgal962.myshowsreviews.shows.shows.data.retrofit

import com.alopgal962.myshowsreviews.shows.shows.data.util.Constantes.Companion.baseUrl
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val retrofit = retrofit2
        .Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}