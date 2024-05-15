package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.alopgal962.myshowsreviews.shows.shows.ui.components.MostrarShow
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.RegisterLoginVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(RegisterloginVM: RegisterLoginVM,GenericVM:GenericVM ,navController: NavController){
    val lista by GenericVM.listashow.collectAsState()
    Scaffold(topBar = { Topbar() }, bottomBar = { BottomBar({ GenericVM.obtenerPeliculas() },{},{},{navController.navigate(Routes.stadisticsRoute.route)}) }) {
        Column(
            Modifier
                .padding(top = 110.dp, bottom = 100.dp)
                .fillMaxSize()
                .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(40.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(text = "\uD83D\uDD0E  Descubir Peliculas",color = Color.Black, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, fontSize = 16.sp)
                IconButton(onClick = {
                    GenericVM.refresh()
                    GenericVM.obtenerPeliculas()
                }) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Refrescar", tint = Color.Black,modifier = Modifier.size(25.dp,25.dp))
                }
            }
            if (lista.isNotEmpty()){
                Column(modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(320.dp)) {
                    LazyHorizontalGrid(
                        modifier = Modifier
                            .height(320.dp)
                            .padding(start = 15.dp, end = 15.dp),
                        rows = GridCells.Fixed(1),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        items(lista){
                            MostrarShow(Show = it,{},{})
                        }
                    }
                }
            }
        }
    }
}