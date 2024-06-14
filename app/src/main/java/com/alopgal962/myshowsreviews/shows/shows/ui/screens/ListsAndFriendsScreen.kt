package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.BottomBar
import com.alopgal962.myshowsreviews.shows.shows.ui.components.MostrarUsuario
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverPeople(GenericVM:GenericAndApiVM, uservm:UserVM, navController: NavController){
    Scaffold (topBar = { Topbar()}, bottomBar = { BottomBar(
        onCasaClick = { navController.navigate(Routes.mainRoute.route)},
        onSeriesClick = { navController.navigate(Routes.myshowsroute.route) },
        onAmigosClick = {navController.navigate(Routes.addfriendsRoute.route)},
        onConfigClic = { navController.navigate(Routes.stadisticsRoute.route) })}) {
        Column(modifier = Modifier
            .padding(top = 110.dp, bottom = 100.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color(227, 214, 146)), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(text = "Descubrir Personas", fontSize = 16.sp, color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 10.dp))
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "", modifier = Modifier.padding(start = 15.dp).clickable {
                        navController.navigate(Routes.addfriendsRoute.route)
                        uservm.obtenerListaUsuarios()
                    } )
                }
                if (uservm.listaUsuarios.isNotEmpty() == true){
                    LazyVerticalGrid(columns = GridCells.Fixed(1), horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier
                        .fillMaxWidth()
                        .height(560.dp)) {
                        items(uservm.listaUsuarios){
                            MostrarUsuario(usuario = it, userVM = uservm,navController)
                        }
                    }
                }
            }
        }
    }
}
