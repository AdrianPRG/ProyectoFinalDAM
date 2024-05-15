package com.alopgal962.myshowsreviews.shows.shows.data.retrofit.GetApi

import androidx.compose.runtime.MutableState
import com.alopgal962.myshowsreviews.shows.shows.data.model.ShowModel
import com.alopgal962.myshowsreviews.shows.shows.data.model.ShowsModel
import retrofit2.http.GET
import com.alopgal962.myshowsreviews.shows.shows.data.util.Constantes.Companion.peliculas
import com.alopgal962.myshowsreviews.shows.shows.data.util.Constantes.Companion.key
import com.alopgal962.myshowsreviews.shows.shows.data.util.Constantes.Companion.mejorvaloradas
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface GetApiService {

    //
    @GET(peliculas + key)
    suspend fun getPeliculas(@Query("page")pagenum:Int): Response<ShowsModel>

    @GET(peliculas + key)
    suspend fun getPelicula(@Query("query")nombre:String): Response<ShowModel>

    @GET(mejorvaloradas + key)
    suspend fun getMejoresPelis(): Response<ShowsModel>

}