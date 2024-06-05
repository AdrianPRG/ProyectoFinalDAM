package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alopgal962.myshowsreviews.shows.shows.data.model.User
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState

@Composable
fun MostrarUsuario(usuario: User, onAcceptClick:() -> Unit, onDeleteclick:() -> Unit){
    Row(modifier = Modifier
        .padding(top = 30.dp)
        .fillMaxWidth()
        .height(220.dp)
        .background(color = Color(234, 196, 53)), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(110.dp)
                .clip(RoundedCornerShape(3.dp))
                .border(width = 3.dp, color = Color.Black)
                .background(color = Color(222, 200, 140)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = usuario.nombre.toString(), fontStyle = FontStyle.Normal, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = Color.Black, textAlign = TextAlign.Center, modifier = Modifier.padding(5.dp))
                Image(painter = ReturnProfile(imageString = usuario.image.toString()), contentDescription = "Imagen de usuario",modifier = Modifier
                    .padding(top = 10.dp)
                    .size(60.dp, 60.dp)
                    .clip(RoundedCornerShape(40.dp)))
                Spacer(modifier = Modifier.padding(top = 3.dp, bottom = 3.dp).fillMaxWidth().height(1.dp).border(width = 1.dp, color = Color.White))
                Text(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),text = "Series Calificadas:", color = Color.Black, fontFamily = FontFamily.Serif, textAlign = TextAlign.Center, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Text(text = usuario.listaSeries?.count().toString(), modifier = Modifier.padding(top = 10.dp), color = Color.Black, fontFamily =  FontFamily.Serif, textAlign =  TextAlign.Center)
            }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            LazyHorizontalGrid(modifier = Modifier.padding(15.dp), rows = GridCells.Fixed(1)) {
                items(usuario.listaSeries!!){
                    MostrarShowUsuario(show = it)
                }
            }
        }
    }
}

@Composable
fun MostrarShowUsuario(show: ShowState){
    Column(modifier = Modifier
        .size(200.dp, 200.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(color = Color(35, 54, 71)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)) {
            AsyncImage(model = "https://image.tmdb.org/t/p/w500${show.imagen}", contentDescription = "Imagen Show", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(text = "Friend Rate:" + show.mipuntuacion, fontSize = 13.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold )
                Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = Color.Yellow, modifier = Modifier
                    .padding(start = 5.dp)
                    .size(30.dp, 30.dp))
            }
        }
    }
}
