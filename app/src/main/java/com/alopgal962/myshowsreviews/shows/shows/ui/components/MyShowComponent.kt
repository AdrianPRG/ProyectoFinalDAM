package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@Composable
fun MyShow(show:ShowState,userVM: UserVM){
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(30.dp))
        .background(color = Color(35, 54, 71))) {
        Column(modifier = Modifier
            .size(170.dp, 230.dp)
            .background(color = Color.Yellow)) {
            AsyncImage(model = "https://image.tmdb.org/t/p/w500${show.imagen}", contentDescription = "Imagen Show", modifier = Modifier
                .fillMaxSize()
                .scale(1.15f, 1f))
        }
        Column(modifier = Modifier
            .size(230.dp, 230.dp)) {
            Text(text = show.titulo.toString(), fontFamily = FontFamily.Serif, color = Color.White, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 10.dp, start = 10.dp), fontSize = 13.sp)
            Row(modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .size(230.dp, 40.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text ="Mi puntuacion " + show.mipuntuacion, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                Icon(imageVector = Icons.Default.Star, contentDescription = "StarUser" , tint = Color.Yellow, modifier = Modifier
                    .padding(start = 10.dp, bottom = 5.dp)
                    .size(30.dp, 30.dp))
            }
            Row(modifier = Modifier
                .padding(start = 10.dp)
                .size(230.dp, 40.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Puntuacion media " + show.puntuacion?.substring(0,3),color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                Icon(imageVector = Icons.Default.Star, contentDescription = "StarUsers" , tint = Color.Blue, modifier = Modifier
                    .padding(start = 10.dp, bottom = 5.dp)
                    .size(30.dp, 30.dp))
            }
            Row(modifier = Modifier
                .padding(top = 10.dp)
                .size(230.dp, 40.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar Pelicula", modifier = Modifier.size(40.dp,40.dp).clickable {
                    userVM.deletePelicula(show) }, tint = Color.Red )
            }
        }
    }
}