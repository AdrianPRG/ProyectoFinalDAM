package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.BottomBar
import com.alopgal962.myshowsreviews.shows.shows.ui.components.ReturnProfile
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(UserVM:UserVM, GenericAndApiVM:GenericAndApiVM, navController: NavController) {
    val user by UserVM.user.collectAsState()
    Scaffold(topBar = { Topbar() }, bottomBar = {
        BottomBar(
            onCasaClick = { navController.navigate(Routes.mainRoute.route) },

            onSeriesClick = { /*TODO*/ },
            onAmigosClick = { /*TODO*/ },
            onConfigClic = { /*TODO*/ })
    }) {
        Column(modifier = Modifier
            .padding(top = 110.dp, bottom = 100.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier
                .padding(top = 30.dp)
                .size(320.dp, 300.dp)
                .clip(
                    RoundedCornerShape(45.dp)
                )
                .background(color = Color(35, 54, 71)), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Cuenta de ${user.name}", color = Color.White, modifier = Modifier.padding(top = 15.dp), fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                Image(painter = ReturnProfile(imageString = user.image.toString()), contentDescription = "imagen", modifier = Modifier
                    .padding(top = 20.dp)
                    .size(100.dp, 100.dp)
                    .clip(
                        RoundedCornerShape(40)
                    ))
                Row(modifier = Modifier.padding(top = 20.dp).size(250.dp,45.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "Imagen Series", tint = Color.White, modifier = Modifier.padding(end = 10.dp).size(30.dp,30.dp))
                    Text(text = "Series Calificadas: ${user.listaSeries?.count()}",fontFamily = FontFamily.Serif, color = Color.White)
                }
                Row(modifier = Modifier.padding(top = 20.dp).size(250.dp,45.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Icon(imageVector = Icons.Default.Face, contentDescription = "Imagen Series", tint = Color.White, modifier = Modifier.padding(end = 10.dp).size(30.dp,30.dp))
                    Text(text = "Numero de amigos: ${user.listaAmigos?.count()}",fontFamily = FontFamily.Serif, color = Color.White)
                }
            }
            Row(
                Modifier
                    .padding(top = 15.dp)
                    .size(320.dp, 120.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Column(modifier = Modifier
                    .size(140.dp, 100.dp)
                    .clip(RoundedCornerShape(20))
                    .background(color = Color(35, 54, 71)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "Eliminar series", fontSize = 13.sp, color = Color.White, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Icon delete", tint = Color.Red, modifier = Modifier
                        .padding(top = 20.dp)
                        .size(30.dp, 30.dp))
                }
                Column(modifier = Modifier
                    .padding(start = 20.dp)
                    .size(140.dp, 100.dp)
                    .clip(RoundedCornerShape(20))
                    .background(color = Color(35, 54, 71)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "Cerrar Sesion", fontSize = 13.sp, color = Color.White, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Icon LogOut", tint = Color.Yellow,modifier = Modifier
                        .padding(top = 20.dp)
                        .size(30.dp, 30.dp)
                        .clickable { UserVM.cerrarSesion { navController.navigate(Routes.loginRoute.route) } })
                }

            }
        }
    }
}

