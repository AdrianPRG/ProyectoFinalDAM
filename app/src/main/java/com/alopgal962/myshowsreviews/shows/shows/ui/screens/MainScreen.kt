package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.R
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
    val estado by GenericAndApiVM.estado.collectAsState()
    val context = LocalContext.current.applicationContext
    LaunchedEffect(true){
        GenericAndApiVM.obtenerPeliculas(GenericAndApiVM.numpage)
    }
    Scaffold(topBar = { Topbar() }, bottomBar = {
        BottomBar({ GenericAndApiVM.obtenerPeliculas(numpagina = GenericAndApiVM.numpage) },
            { navController.navigate(Routes.myshowsroute.route) },
            { navController.navigate(Routes.addfriendsRoute.route) },
            { navController.navigate(Routes.stadisticsRoute.route) })
    }) {
        if (estado==true){
            //Columna principal de la pantalla
            Column(
                Modifier
                    .padding(top = 110.dp, bottom = 100.dp)
                    .fillMaxSize()
                    .background(color = Color(232, 239, 236)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Fila que contiene el nombre de usuario
                Row(modifier = Modifier
                    .size(270.dp, 30.dp)
                    .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                    .background(color = Color(35, 54, 71)), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(text = "Bienvenido, ${user.nombre}", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, fontFamily = FontFamily.Serif)
                }
                //Fila que contiene los iconos de avanzar y retroceder el numero de paginas
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = { GenericAndApiVM.refresh(false) }, enabled = bool) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = "Atras",
                            tint = Color.Black,
                            modifier = Modifier.size(25.dp, 25.dp)
                        )
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
                // Numero de pagina
                Text(
                    text = "Pagina ${GenericAndApiVM.numpage}",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                //Columna en la que se mostrarán la lista de series si no esta vacia
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
                                MostrarShow(Show = it, oninfoclick =
                                {
                                    //Cuando se pulse sobre el boton de infomacion, se navegará a la pantalla de la serie
                                    GenericAndApiVM.obtenerPelicula(it.titulo.toString())
                                    navController.navigate("ShowExplained/${it.titulo}")
                                }, onanadirclick =
                                { //Se comprueba si la serie esta en la lista de usuario, si no es asi, se navega a la pantalla de añadir serie
                                    UserVM.comprobacionSerie(it,{ navController.navigate("AddShow/${it.titulo}") }, context = context)
                                }, userVM =  UserVM
                                )
                            }
                        }
                    }
                }
                //Si la lista esta vacia, se mostrará un indicador de progreso y un texto de cargando..
                else {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        CircularProgressIndicator(
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 60.dp)
                                .size(100.dp, 100.dp)
                        )
                        Text(text = "Cargando...", fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, color = Color.Black, modifier = Modifier.padding(top = 30.dp))
                    }
                }
            }
        }
        else{
            Column(modifier = Modifier.padding(top = 110.dp, bottom = 100.dp).fillMaxSize().background(color = Color(232, 239, 236)), Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.errorinternet), contentDescription = "Error internet", modifier = Modifier.size(80.dp,80.dp))
                Text(text = "Error en la conexion a internet", modifier = Modifier.padding(top = 20.dp),fontSize = 17.sp, fontWeight = FontWeight.SemiBold, color = Color.Black, fontFamily = FontFamily.Serif)
                Button(onClick = {navController.navigate(Routes.mainRoute.route)}, modifier = Modifier.padding(top = 20.dp),colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)) {
                    Text(text = "Volver a intentar",color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                }
            }
        }
        }
    }