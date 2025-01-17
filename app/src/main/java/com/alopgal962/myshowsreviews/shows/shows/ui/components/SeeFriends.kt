package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alopgal962.myshowsreviews.R
import com.alopgal962.myshowsreviews.shows.shows.data.model.User
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@Composable
fun MostrarUsuario(usuario: User,userVM: UserVM,navController: NavController){
    Row(modifier = Modifier
        .padding(top = 20.dp)
        .fillMaxWidth()
        .height(260.dp)
        .background(color = Color(234, 196, 53)), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(110.dp)
                .clip(RoundedCornerShape(3.dp))
                .border(width = 3.dp, color = Color.Black)
                .background(color = Color(222, 200, 140)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = usuario.nombre.toString(), textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, fontSize = 13.5.sp, fontFamily = FontFamily.Serif, color = Color.Black, modifier = Modifier.padding(end = 5.dp, start = 5.dp))
                    Image(painter = ReturnProfile(imageString = usuario.image.toString()), contentDescription = "Foto Usuario",
                        Modifier
                            .padding(20.dp)
                            .size(60.dp, 60.dp)
                            .clip(RoundedCornerShape(30.dp)))
                }
                Spacer(modifier = Modifier
                    .padding(top = 3.dp, bottom = 3.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .border(width = 1.dp, color = Color.White))
                Text(modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),text = "Series Calificadas:", color = Color.Black, fontFamily = FontFamily.Serif, textAlign = TextAlign.Center, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Text(text = usuario.listaSeries?.count().toString(), modifier = Modifier.padding(top = 10.dp), color = Color.Black, fontFamily =  FontFamily.Serif, textAlign =  TextAlign.Center)
            }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            LazyHorizontalGrid(modifier = Modifier.padding(15.dp), rows = GridCells.Fixed(1),horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                if (usuario.listaSeries == emptyList<ShowState>()){
                    item {
                        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Text(text = "El usuario no ha calificado ninguna serie", textAlign = TextAlign.Center ,modifier = Modifier ,color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                            Image(painter = painterResource(id = R.drawable.noseries), contentDescription = "Imagen no serie disponible", modifier = Modifier.padding(top = 20.dp).size(70.dp,70.dp))
                        }
                    }
                }
                else{
                    items(usuario.listaSeries!!){
                        MostrarShowUsuario(show = it,{
                            navController.navigate("UserShowExplained/${usuario.nombre}_${it.titulo}")
                            userVM.setUser(usuario,it)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun MostrarShowUsuario(show: ShowState,navegacion:() -> Unit){
    Column(modifier = Modifier
        .size(220.dp, 240.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(color = Color(35, 54, 71)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)) {
            AsyncImage(model = "https://image.tmdb.org/t/p/w500${show.imagen}", contentDescription = "Imagen Show", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier
                .padding(top = 5.dp, start = 5.dp, end = 5.dp)
                .fillMaxWidth()
                .height(30.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = Color.Yellow, modifier = Modifier
                    .padding(end = 5.dp)
                    .size(30.dp, 30.dp))
                Text(text = "Puntuacion de amigo: " + show.mipuntuacion, modifier = Modifier.padding(start = 5.dp),fontSize = 12.5.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
            Row(modifier = Modifier
                .padding(top = 5.dp, start = 5.dp, end = 5.dp)
                .fillMaxWidth()
                .height(90.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "", tint = Color.White, modifier = Modifier
                    .padding(end = 5.dp)
                    .size(30.dp, 30.dp))
                Text(text = "Click para ver reseña", modifier = Modifier
                    .padding(end = 10.dp, start = 5.dp)
                    .clickable {
                        navegacion() }, fontSize = 13.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, color = Color.White )
            }
        }
    }
}
