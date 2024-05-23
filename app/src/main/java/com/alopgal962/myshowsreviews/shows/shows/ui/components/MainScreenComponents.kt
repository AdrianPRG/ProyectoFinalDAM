package com.alopgal962.myshowsreviews.shows.shows.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState

@Composable
fun MostrarShow(Show:ShowState, oninfoclick:() -> Unit, onanadirclick:() -> Unit){
    Column(modifier = Modifier
        .size(270.dp, 360.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(color = Color(35, 54, 71)),horizontalAlignment = Alignment.CenterHorizontally){
        Column(Modifier.size(270.dp,190.dp)) {
            AsyncImage(model = "https://image.tmdb.org/t/p/w500${Show.imagen}", contentDescription = "${Show.titulo} / imagen",
                Modifier
                    .fillMaxSize()
                    .scale(2.5f, 1f) )
        }
        Column(
            Modifier.size(270.dp, 110.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = Show.titulo!!, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold ,fontFamily = FontFamily.Serif, modifier = Modifier.padding(top = 10.dp))
            Text(text = "Puntuacion: " + Show.puntuacion?.substring(0,3) + " ⭐ ", Modifier.padding(top = 10.dp), fontSize = 13.sp ,color = Color.White,fontFamily = FontFamily.Serif)
            Text(text = "Numero de votos: " + Show.votos, Modifier.padding(top = 10.dp) ,fontSize = 13.sp , color = Color.White,fontFamily = FontFamily.Serif)
        }
        Column(
            Modifier
                .padding(bottom = 15.dp)
                .size(270.dp, 40.dp)) {
            Row(modifier = Modifier
                .fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { oninfoclick() }) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "Ver Pelicula", tint = Color.White,modifier = Modifier.size(40.dp,40.dp))
                }
                IconButton(onClick = { onanadirclick() }, modifier = Modifier
                    .padding(start = 20.dp)){
                    Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "Añadir Pelicula", tint = Color.White, modifier = Modifier.size(40.dp,40.dp))
                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostrarShowFully(show:ShowState, onExitClick:() -> Unit){
    Scaffold(topBar = { Topbar() }) {
        Column(modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(text = "Serie:  ${show.titulo}", modifier = Modifier.padding(top = 20.dp), color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)) {
                AsyncImage(model = "https://image.tmdb.org/t/p/w500${show.imagen}", contentDescription = "${show.titulo} / fullyShow / imagen", Modifier.fillMaxSize()) }
            Column(modifier = Modifier.fillMaxWidth().height(340.dp).background(color = Color(217, 205, 158))) {
                Text(text = "Descripcion de la serie",color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                Text(text = show.descripcion.toString(), modifier = Modifier.padding(top = 12.dp),color = Color.Black, fontFamily = FontFamily.Serif,)
            }
            Button(onClick = { onExitClick() }) {
                Text(text = "Volver atras")
            }
        }
    }

}
