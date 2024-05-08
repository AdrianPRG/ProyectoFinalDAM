package com.alopgal962.myshowsreviews.shows.shows.data.model

import com.google.gson.annotations.SerializedName

data class ShowsModel(
    @SerializedName("results")
    var resultados:List<ShowModel>
)