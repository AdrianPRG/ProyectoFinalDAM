package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alopgal962.myshowsreviews.R
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.BottomBar
import com.alopgal962.myshowsreviews.shows.shows.ui.components.ReturnProfile
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.RegisterLoginVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(RegisterLoginVM:RegisterLoginVM, GenericVM:GenericVM, navController: NavController) {
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
                .size(300.dp, 300.dp)
                .clip(
                    RoundedCornerShape(45.dp)
                )
                .background(color = Color(35, 54, 71)), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Cuenta de ${RegisterLoginVM.nombreRegister}", color = Color.White, modifier = Modifier.padding(top = 15.dp), fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                Image(painter = ReturnProfile(imageString = RegisterLoginVM.imagenRegister), contentDescription = "imagen", modifier = Modifier
                    .padding(top = 20.dp)
                    .size(100.dp, 100.dp)
                    .clip(
                        RoundedCornerShape(40)
                    ))
                Text(text = "Series Calificadas: ${RegisterLoginVM.listaSeries?.count()}",modifier = Modifier.padding(top = 15.dp),fontFamily = FontFamily.Serif)
                Text(text = "Numero de amigos: ${RegisterLoginVM.listaAmigos?.count()}",modifier = Modifier.padding(top = 15.dp),fontFamily = FontFamily.Serif )
            }
            Column(modifier = Modifier.padding(top = 30.dp).size(70.dp,70.dp).clip(
                RoundedCornerShape(20))) {

            }
        }
    }
}

