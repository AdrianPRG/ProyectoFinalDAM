package com.alopgal962.myshowsreviews.shows.shows.ui.state

import com.alopgal962.myshowsreviews.shows.shows.data.model.ShowModel

data class ShowsState(
    var resultados: List<ShowState> = emptyList()
)