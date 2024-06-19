package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alopgal962.myshowsreviews.R
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@Composable
fun MyShow(show:ShowState,userVM: UserVM,navController: NavController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(30.dp))
        .background(color = Color(35, 54, 71))) {
        Column(modifier = Modifier
            .size(170.dp, 230.dp)
            .background(color = Color.Yellow)) {
            AsyncImage(model = "https://image.tmdb.org/t/p/w500${show.imagen}", error = painterResource(
                id = R.drawable.nodisponible
            ) ,contentDescription = "Imagen Show", modifier = Modifier
                .fillMaxSize()
                .scale(1.15f, 1f))
        }
        Column(modifier = Modifier
            .size(230.dp, 230.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .size(230.dp, 40.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "StarUser" , tint = Color.Yellow, modifier = Modifier
                    .padding(end = 10.dp, bottom = 5.dp)
                    .size(30.dp, 30.dp))
                Text(text ="Mi puntuacion: " + show.mipuntuacion, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
            Row(modifier = Modifier
                .padding(start = 10.dp)
                .size(230.dp, 40.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "StarUsers" , tint = Color.White, modifier = Modifier
                    .padding(end = 10.dp, bottom = 5.dp)
                    .size(30.dp, 30.dp))
                Text(text = "Puntuaje medio: " + show.puntuacion?.toString()?.substring(0,3),color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
            Row(modifier = Modifier
                .padding(start = 10.dp)
                .size(230.dp, 60.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Reseña" , tint = Color.White, modifier = Modifier
                    .padding(end = 5.dp, bottom = 5.dp)
                    .size(30.dp, 30.dp))
                Text(text = "Click en detalles para ver reseña...",color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(end = 10.dp, start = 5.dp, bottom = 10.dp))
            }
            Row(modifier = Modifier
                .width(230.dp).fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.fillMaxHeight().width(115.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "Eliminar", color = Color.White, fontFamily = FontFamily.Serif, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Icono de eliminar show", tint = Color.Red, modifier = Modifier
                        .padding(top = 10.dp)
                        .size(30.dp, 30.dp)
                        .clickable {
                            userVM.deletePelicula(show)
                            userVM.obtenerListaUsuarios()
                            navController.navigate(Routes.myshowsroute.route)
                        })
                }
                Column(modifier = Modifier.padding(end = 10.dp).fillMaxHeight().width(115.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "Detalles", color = Color.White, fontFamily = FontFamily.Serif, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Icono de ver show", tint = Color.White, modifier = Modifier
                        .padding(top = 10.dp)
                        .size(30.dp, 30.dp)
                        .clickable {
                            userVM.seeFullyShowDetails(show)
                            navController.navigate("MyShowExplained/${show.titulo}")
                        })
                }
            }
        }
    }
}
