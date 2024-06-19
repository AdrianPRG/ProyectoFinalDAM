package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.ReturnProfile
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenSerieUsuario(userVM: UserVM,navController: NavController){
    val user by userVM.usuarioSerie.collectAsState()
    val show by userVM.serieUsuario.collectAsState()
    Scaffold(topBar = { Topbar()}) {
        Column(
            Modifier
                .padding(top = 110.dp)
                .fillMaxSize()
                .background(color = Color(232, 239, 236))) {
           Column(modifier = Modifier
               .fillMaxWidth()
               .height(90.dp)
               .border(2.dp, color = Color.Black), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "Show de usuario", color = Color.Black, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif)
              Text(text = show.titulo.toString(), color = Color.Black, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, modifier = Modifier.padding(10.dp), textAlign = TextAlign.Center, fontSize = 15.sp)
           }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp)
                    .border(2.dp, color = Color.Black)) {
                    AsyncImage(model = "https://image.tmdb.org/t/p/w500${show.imagen}", modifier = Modifier.fillMaxSize(), contentDescription = "Imagen show", contentScale = ContentScale.Crop)
                }
                Column(modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, color = Color.Black), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = user.nombre.toString(), color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, modifier = Modifier.padding(top = 7.dp))
                    Image(painter = ReturnProfile(imageString = user.image.toString()), contentDescription = "Imagen de usuario", modifier = Modifier
                        .padding(top = 10.dp)
                        .size(80.dp, 80.dp)
                        .clip(
                            RoundedCornerShape(30.dp)
                        ))
                    Row(modifier = Modifier
                        .padding(start = 15.dp)
                        .fillMaxWidth()
                        .height(50.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star,"Icono de estrella usuario", tint = Color.Black, modifier = Modifier.size(25.dp,25.dp))
                        Text(text = "Puntuacion: ${show.mipuntuacion}", modifier = Modifier.padding(start = 5.dp), color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    }
                    Column(Modifier.fillMaxSize().border(2.dp,color = Color.Black)) {
                        Row(modifier = Modifier
                            .padding(start = 15.dp)
                            .fillMaxWidth()
                            .height(50.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "Icono de estrella", tint = Color.Yellow, modifier = Modifier
                                .padding(end = 5.dp)
                                .size(25.dp, 25.dp))
                            Text(text = "Puntuacion: ${userVM.checkShowRate(show)}", color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }
                        Row(modifier = Modifier
                            .padding(start = 15.dp)
                            .fillMaxWidth()
                            .height(50.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Icono de estrella", tint = Color.Blue, modifier = Modifier
                                .padding(end = 5.dp)
                                .size(25.dp, 25.dp))
                            Text(text = "Votos: ${userVM.checkShowRate(show)}", color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }
                        Row(modifier = Modifier
                            .padding(start = 15.dp)
                            .fillMaxWidth()
                            .height(50.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.DateRange, contentDescription = "Icono de estrella", tint = Color.Black, modifier = Modifier
                                .padding(end = 5.dp)
                                .size(25.dp, 25.dp))
                            Text(text = "Estreno: ${show.fechasalida}", color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        }
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .border(2.dp, color = Color.Black), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Reseña de ${user.nombre}", color = Color.Black, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, fontSize = 16.sp)
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(text = "Ir atras", color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                    Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Icono Salir", tint = Color.Black, modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable { userVM.OnExitUserSerie { navController.navigate(Routes.addfriendsRoute.route) } })
                }
            }
            LazyColumn(modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxSize()){
                item {
                    Text(text = show.miresena.toString(), color = Color.Black, fontSize = 14.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(7.dp))
                }
            }
        }
    }
}