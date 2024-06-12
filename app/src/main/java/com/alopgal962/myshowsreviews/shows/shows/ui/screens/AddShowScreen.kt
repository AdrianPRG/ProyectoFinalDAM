package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
fun AddShow(userVM: UserVM,navController: NavController){
    val showInsertar by userVM.showInsertar.collectAsState()
    Scaffold(topBar = { Topbar()}) {
        Column(modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxSize()
            .background(color = Color(225, 227, 227)), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    Column(modifier = Modifier
                        .size(350.dp, 100.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(color = Color(216, 224, 237))
                        .border(width = 5.dp, color = Color.Black), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(text = "\uD83D\uDCFA  Show a calificar  \uD83D\uDCFA", color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 20.sp )
                        Text(text = showInsertar.titulo.toString(), textAlign = TextAlign.Center,modifier = Modifier.padding(top = 10.dp, end = 10.dp, start = 10.dp),color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 14.sp )
                    }
        }
        LazyColumn(modifier = Modifier
            .padding(end = 25.dp, start = 25.dp, bottom = 30.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(5.dp))
            .border(width = 5.dp, color = Color.Black)
            .background(color = Color(216, 224, 237)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Column(
                    Modifier
                        .padding(top = 20.dp, end = 20.dp, start = 20.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp))
                        .border(width = 5.dp, color = Color.Black)) {
                    if (showInsertar.imagen==""){
                        Image(painter = painterResource(id = R.drawable.nodisponible), contentDescription = "Imagen No disponible", modifier = Modifier.fillMaxSize())
                    }
                    else{
                        AsyncImage(model = "https://image.tmdb.org/t/p/w500${showInsertar.imagen}", contentDescription = "Descripcion", contentScale = ContentScale.FillBounds, modifier = Modifier.fillMaxSize())
                    }
                }
                Row(modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(100.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    TextField(modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .width(275.dp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),placeholder = { Text("Puntuacion del 1 al 10", color = Color.White)},value = userVM.puntuacion, onValueChange = { if (userVM.puntuacion.length<2){ userVM.puntuacion = it}
                                                                                                                                                                                                                                                                                       else{userVM.puntuacion=""}}, label = { Text(
                        text = "Introduce tu puntuacion..", color = Color.White
                    )}, leadingIcon = { Icon(imageVector = Icons.Default.Star, contentDescription = "", tint = Color.White)}, singleLine = true, maxLines = 1, colors = TextFieldDefaults.textFieldColors(containerColor = Color(35, 54, 71), textColor = Color.White))
                }
                Row(modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(100.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    TextField(modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .width(275.dp), maxLines = 3,placeholder = { Text("Increible, me ha encantado", color = Color.White, textAlign = TextAlign.Center)},value = userVM.resena, onValueChange = { userVM.resena = it }, label = { Text(
                        text = "Introduce tu reseña", color = Color.White
                    )},leadingIcon = {Icon(imageVector = Icons.Default.Edit, contentDescription = "", tint = Color.White)}, colors = TextFieldDefaults.textFieldColors(containerColor = Color(35, 54, 71), textColor = Color.White))
                }
                Row(modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .height(100.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = { userVM.anadirSerieDB {
                        navController.navigate(Routes.mainRoute.route)
                        userVM.obtenerListaUsuarios()
                    } }, colors = ButtonDefaults.buttonColors(containerColor = Color(61, 102, 63))) {
                        Text(text = "Insertar",fontFamily = FontFamily.Serif, color = Color.White)
                        Icon(imageVector = Icons.Default.Add, contentDescription = "", modifier = Modifier.padding(start = 10.dp))
                    }
                    Button(onClick = { userVM.ResetSerieValues() }, modifier = Modifier.padding(start = 20.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(121, 29, 22 ))) {
                        Text(text = "Vaciar", fontFamily = FontFamily.Serif, color = Color.White)
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "", modifier = Modifier.padding(start = 10.dp))
                    }
                }
            }
        }
    }
}
}