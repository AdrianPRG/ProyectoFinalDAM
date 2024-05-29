package com.alopgal962.myshowsreviews.shows.shows.data
import androidx.compose.runtime.MutableState
import com.alopgal962.myshowsreviews.shows.shows.data.model.ShowModel
import com.alopgal962.myshowsreviews.shows.shows.data.model.ShowsModel
import com.alopgal962.myshowsreviews.shows.shows.data.retrofit.GetApi.GetApiService
import com.alopgal962.myshowsreviews.shows.shows.data.retrofit.Retrofit
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowsState

class ShowRepository {

    private val service = Retrofit.retrofit.create(GetApiService::class.java)
     suspend fun GetShows(num:Int) :ShowsState {
        val response = service.getPeliculas(num)
        return if (response.isSuccessful) {
            response.body()!!.toShowsState()
        }
        else {
            ShowsState()
        }

    }

    private fun ShowsModel.toShowsState():ShowsState{
        return ShowsState(

            resultados = this.resultados.map { it.toShowState() }
        )
    }
    private fun ShowModel.toShowState():ShowState{
        return ShowState(
            titulo = this.titulo,
            descripcion = this.descripcion,
            imagen = this.imagen,
            puntuacion = this.puntuacion,
            votos = this.votos,
            fechasalida = this.fechasalida,
            mipuntuacion = 0,
            miresena = ""
        )
    }


}