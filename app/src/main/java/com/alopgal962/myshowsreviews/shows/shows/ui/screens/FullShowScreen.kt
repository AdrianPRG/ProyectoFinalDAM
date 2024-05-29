package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowExplained(GenericAndApiVM:GenericAndApiVM, navController: NavController){
    val show by GenericAndApiVM.show.collectAsState()
    Scaffold(topBar = { Topbar()}) {
        Column(modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .border(width = 2.5.dp, color = Color.Black)
                .background(color = Color(225, 227, 227)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Titulo:", fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, color = Color.Black)
                Text(text = show.titulo.toString(), modifier = Modifier.padding(top = 15.dp, end = 10.dp, start = 10.dp), fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, color = Color.Black)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .background(color = Color(225, 227, 227))) {
                Column(modifier = Modifier.size(200.dp,330.dp).border(2.5.dp, color = Color.Black).background(color = Color(225, 227, 227))) {
                    AsyncImage(model = "https://image.tmdb.org/t/p/w500${show.imagen}", contentDescription = "Descripcion", contentScale = ContentScale.Crop, modifier = Modifier
                        .fillMaxSize())
                }
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp)
                    .border(2.5.dp, color = Color.Black)
                    .background(color = Color(225, 227, 227))) {
                    item{
                        Text(text = "Descripcion:", modifier = Modifier.padding(top = 20.dp, start = 10.dp), fontSize = 17.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, color = Color.Black)
                        Text(text = "${show.descripcion}", modifier = Modifier.padding(end = 20.dp, top = 20.dp, start = 10.dp, bottom = 15.dp), fontFamily = FontFamily.Serif, color = Color.Black)
                    }
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .border(width = 2.5.dp,color = Color.Black)
                .background(color = Color(216, 224, 237)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                Row(modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center ) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Star Rate", tint = Color.Yellow, modifier = Modifier
                        .padding(end = 10.dp)
                        .size(30.dp, 30.dp))
                    Text(text = "Puntuacion: ${show.puntuacion?.substring(0,3)}", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, color = Color.Black)

                }
                Row(modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(40.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Vote Count", tint = Color.Blue, modifier = Modifier
                        .padding(end = 10.dp)
                        .size(25.dp, 25.dp))
                    Text(text = "Numero de votos: ${show.votos}", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Row(modifier = Modifier
                    .padding(top = 20.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .height(40.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Vote Count", tint = Color.Black, modifier = Modifier
                        .padding(end = 10.dp)
                        .size(25.dp, 25.dp))
                    Text(text = "Fecha de estreno: ${show.fechasalida}", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
            Row(modifier = Modifier
                .fillMaxSize()
                .border(width = 2.5.dp, color = Color.Black)
                .background(color = Color(225, 227, 227)), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.Black, modifier = Modifier.size(40.dp,40.dp).clickable { navController.navigate(Routes.mainRoute.route) })
            }
        }
    }
}