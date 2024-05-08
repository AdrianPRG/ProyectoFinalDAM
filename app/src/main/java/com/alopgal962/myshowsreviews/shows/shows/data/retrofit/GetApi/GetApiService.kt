package com.alopgal962.myshowsreviews.shows.shows.data.retrofit.GetApi

import com.alopgal962.myshowsreviews.shows.shows.data.model.ShowModel
import com.alopgal962.myshowsreviews.shows.shows.data.model.ShowsModel
import retrofit2.http.GET
import com.alopgal962.myshowsreviews.shows.shows.data.util.Constantes.Companion.peliculas
import com.alopgal962.myshowsreviews.shows.shows.data.util.Constantes.Companion.key
import com.alopgal962.myshowsreviews.shows.shows.data.util.Constantes.Companion.mejorvaloradas
import retrofit2.Response

interface GetApiService {

    @GET(peliculas + key)
    suspend fun getPeliculas(): Response<ShowsModel>

    @GET(mejorvaloradas + key)
    suspend fun getMejoresPelis(): Response<List<ShowModel>>

}