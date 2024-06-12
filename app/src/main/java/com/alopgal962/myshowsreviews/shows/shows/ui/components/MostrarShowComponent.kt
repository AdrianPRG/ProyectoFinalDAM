package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@Composable
fun MostrarShow(Show:ShowState, oninfoclick:() -> Unit, onanadirclick:() -> Unit, userVM: UserVM){
    Column(modifier = Modifier
        .size(270.dp, 360.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(color = Color(35, 54, 71)),horizontalAlignment = Alignment.CenterHorizontally){
        Column(Modifier.size(270.dp,190.dp)) {
            AsyncImage(model = "https://image.tmdb.org/t/p/w500${Show.imagen}", contentDescription = "${Show.titulo} / imagen", contentScale = ContentScale.Crop,
               modifier =  Modifier
                    .fillMaxSize())
        }
        Column(
            Modifier.padding(top = 10.dp ).size(270.dp, 80.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = userVM.setShortTitle(Show), textAlign = TextAlign.Center, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold ,fontFamily = FontFamily.Serif, modifier = Modifier.padding(top = 10.dp))
            Text(text = "Puntuacion: " + userVM.checkShowRate(Show) + " ⭐ ", Modifier.padding(top = 10.dp), fontSize = 13.sp ,color = Color.White,fontFamily = FontFamily.Serif)
            Text(text = "Numero de votos: " + Show.votos, Modifier.padding(top = 10.dp) ,fontSize = 13.sp , color = Color.White,fontFamily = FontFamily.Serif)
        }
        Column(
            Modifier
                .size(270.dp, 80.dp)) {
            Row(modifier = Modifier
                .fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { oninfoclick() }) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "Ver Pelicula", tint = Color.White,modifier = Modifier.size(90.dp,70.dp))
                }
                IconButton(onClick = { onanadirclick() }, modifier = Modifier
                    .padding(start = 20.dp)){
                    Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "Añadir Pelicula", tint = Color.White, modifier = Modifier.size(90.dp,70.dp))
                }
            }
        }
    }
}