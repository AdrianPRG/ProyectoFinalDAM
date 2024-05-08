package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState

@Composable
fun MostrarShow(Show:ShowState){
    Column(modifier = Modifier.size(200.dp,200.dp),horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = Show.titulo + Show.descripcion, color = Color.Black)
    }
}
