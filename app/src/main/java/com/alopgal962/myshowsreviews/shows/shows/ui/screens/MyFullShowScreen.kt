package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
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
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFullShowScreen(userVM: UserVM,navController: NavController){
    val show by userVM.showEstadisticas.collectAsState()
    val desc by userVM.desc.collectAsState()
    Scaffold(topBar = { Topbar()}) {
        Column(modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236))) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(5.dp))
                .border(2.dp, color = Color.Black), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Show:", color = Color.Black, fontFamily = FontFamily.Serif, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 5.dp))
                Text(text = show.titulo.toString(), modifier = Modifier.padding(10.dp) ,color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 16.sp,textAlign = TextAlign.Center)
            }
            Column(modifier = Modifier.fillMaxSize()) {
                Column{
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                        .border(3.dp, color = Color.Black)) {
                        Column(modifier = Modifier
                            .fillMaxHeight()
                            .width(200.dp)
                            .border(3.dp, color = Color.Black)) {
                            AsyncImage(model = "https://image.tmdb.org/t/p/w500${show.imagen}", error = painterResource(
                                id = R.drawable.nodisponible
                            ) ,modifier = Modifier.fillMaxSize(), contentDescription = "Imagen show", contentScale = ContentScale.Crop)
                        }
                        LazyColumn(modifier = Modifier.fillMaxSize()){
                            item {
                                Column(modifier = Modifier
                                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                                    Row(modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                        .border(
                                            3.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(5.dp)
                                        )) {
                                        Text(text = "Estadisticas de Show", modifier = Modifier.padding(10.dp), color = Color.Black, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, fontSize = 15.sp, fontStyle = FontStyle.Italic)
                                    }
                                    Row(modifier = Modifier
                                        .padding(top = 5.dp, start = 10.dp)
                                        .fillMaxWidth()
                                        .height(30.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.Star, contentDescription = "Icono Rating", tint = Color.Yellow, modifier = Modifier.size(30.dp,30.dp))
                                        Text(text = "Puntuacion: ${userVM.checkShowRate(show)}", modifier = Modifier.padding(start = 10.dp),color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                                    }
                                    Row(modifier = Modifier
                                        .padding(start = 10.dp, top = 7.dp)
                                        .fillMaxWidth()
                                        .height(40.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Icono Votos", tint = Color.Blue, modifier = Modifier.size(25.dp,25.dp))
                                        Text(text = "Votos: ${show.votos} ", modifier = Modifier.padding(start = 10.dp), color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                                    }
                                    Row(modifier = Modifier
                                        .padding(start = 10.dp, top = 7.dp)
                                        .fillMaxWidth()
                                        .height(40.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "Icono Fecha", tint = Color.Black, modifier = Modifier.size(30.dp,30.dp))
                                        Text(text = "Fecha: ${show.fechasalida} ", modifier = Modifier.padding(start = 10.dp), color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                                    }
                                    Row(modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                        .border(
                                            3.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(5.dp)
                                        ), horizontalArrangement = Arrangement.Center) {
                                        Text(text = "Mis estadisticas", textAlign = TextAlign.Center ,modifier = Modifier.padding(10.dp), color = Color.Black, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, fontSize = 15.sp, fontStyle = FontStyle.Italic)
                                    }
                                    Row(modifier = Modifier
                                        .padding(start = 10.dp, top = 5.dp)
                                        .fillMaxWidth()
                                        .height(40.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Icon(imageVector = Icons.Default.Star, contentDescription = "Icono Voto propio", tint = Color.Black, modifier = Modifier.size(25.dp,25.dp))
                                        Text(text = "Puntuacion: ${show.mipuntuacion} ", modifier = Modifier.padding(start = 10.dp), color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }
                        }
                    }
                    if (desc){
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .border(2.dp, color = Color.Black), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Icono de descripcion", tint = Color.Black, modifier = Modifier
                                .size(30.dp, 30.dp)
                                .clickable { userVM.changeDescState() })
                            Text(text = "Descripcion", textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, color = Color.Black, fontSize = 15.sp, modifier = Modifier.padding(start = 15.dp, end = 10.dp))
                            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Volver Atras", tint = Color.Black, modifier = Modifier
                                .padding(start = 80.dp)
                                .size(30.dp, 30.dp)
                                .clickable {
                                    userVM.onExitMyFullShowScreen(
                                        { navController.navigate(Routes.myshowsroute.route) })
                                })

                        }
                        LazyColumn(modifier = Modifier
                            .padding(bottom = 40.dp)
                            .fillMaxSize()
                            .border(2.dp,Color.Black)){
                            item {
                                Text(text = show.descripcion.toString(), color = Color.Black, modifier = Modifier.padding(10.dp),fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                    else{
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .border(2.dp, color = Color.Black), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Icono de descripcion", tint = Color.Black, modifier = Modifier
                                .size(30.dp, 30.dp)
                                .clickable { userVM.changeDescState() })
                            Text(text = "Mi Reseña", textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, color = Color.Black, fontSize = 15.sp, modifier = Modifier.padding(start = 15.dp, end = 10.dp))
                            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Volver Atras", tint = Color.Black, modifier = Modifier
                                .padding(start = 80.dp)
                                .size(30.dp, 30.dp)
                                .clickable { userVM.onExitMyFullShowScreen({navController.navigate(Routes.myshowsroute.route)}) })
                        }
                        LazyColumn(modifier = Modifier
                            .padding(bottom = 40.dp)
                            .fillMaxSize()
                            .border(2.dp, color = Color.Black)){
                            item {
                                Text(text = show.miresena.toString(), color = Color.Black, modifier = Modifier.padding(10.dp), fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                    }
                }
            }
        }
    }