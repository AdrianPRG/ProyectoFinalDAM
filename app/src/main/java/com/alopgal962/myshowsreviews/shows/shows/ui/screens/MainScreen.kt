package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    UserVM: UserVM,
    GenericAndApiVM: GenericAndApiVM,
    navController: NavController
) {
    val user by UserVM.user.collectAsState()
    val lista by GenericAndApiVM.listashow.collectAsState()
    val bool by GenericAndApiVM.disabled.collectAsState()
    Scaffold(topBar = { Topbar() }, bottomBar = {
        BottomBar({ GenericAndApiVM.obtenerPeliculas(numpagina = GenericAndApiVM.numpage) },
            {navController.navigate(Routes.myshowsroute.route) },
            {navController.navigate(Routes.addfriendsRoute.route)},
            { navController.navigate(Routes.stadisticsRoute.route) })
    }) {
        Column(
            Modifier
                .padding(top = 110.dp, bottom = 100.dp)
                .fillMaxSize()
                .background(color = Color(232, 239, 236)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (UserVM.addingShow==true){
                Text(text = "Reseña y puntuacion del usuario: Proximamente...", color = Color.Black, fontSize = 15.sp, modifier = Modifier.padding(top = 300.dp))
                Text(text = "Se añadira la serie con valores de usuario nulos", color = Color.Black)
                Button(onClick = { UserVM.changeOnAddingShowState() }) {
                    Text(text = "Volver atras")
                }
            }
            else{
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { GenericAndApiVM.refresh(false) }, enabled = bool) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atras",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp, 25.dp) )
                    }
                    Text(
                        text = "\uD83D\uDD0E  Descubir Peliculas",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Serif,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    IconButton(onClick = {
                        GenericAndApiVM.refresh(true)
                    }, enabled = bool) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Siguiente",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp, 25.dp)
                        )
                    }
                }
                Text(text = "Pagina ${GenericAndApiVM.numpage}", fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold ,color = Color.Black)
                if (lista.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                            .height(360.dp)
                    ) {
                        LazyHorizontalGrid(
                            modifier = Modifier
                                .height(360.dp)
                                .padding(start = 15.dp, end = 15.dp),
                            rows = GridCells.Fixed(1),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            items(lista) {
                                MostrarShow(Show = it,
                                    {
                                        GenericAndApiVM.obtenerPelicula(it.titulo.toString())
                                        navController.navigate("ShowExplained/${it.titulo}")
                                    },
                                    {
                                        UserVM.changeOnAddingShowState()
                                        UserVM.meterSeriesUsuario(it)
                                    })
                            }
                        }
                    }
                }
                else{
                    CircularProgressIndicator(color = Color.Black, modifier = Modifier.padding(top = 60.dp))
                    Text(text = "Cargando...", color = Color.Black)
                }
            }
        }
    }
}