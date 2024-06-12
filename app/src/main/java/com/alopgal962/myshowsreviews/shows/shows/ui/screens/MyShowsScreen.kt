package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.BottomBar
import com.alopgal962.myshowsreviews.shows.shows.ui.components.MyShow
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyShowsScreen(uservm:UserVM,genericAndApiVM: GenericAndApiVM, navController: NavController){
    val user by uservm.user.collectAsState()
    Scaffold (topBar = { Topbar()}, bottomBar = { BottomBar(
        onCasaClick = { navController.navigate(Routes.mainRoute.route) },
        onSeriesClick = { uservm.recuperarSeriesUsuario()},
        onAmigosClick = { navController.navigate(Routes.addfriendsRoute.route)},
        onConfigClic = {navController.navigate(Routes.stadisticsRoute.route)}) }) {
        Column(modifier = Modifier
            .padding(top = 110.dp, bottom = 100.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Mis Series", color = Color.Black, fontStyle = FontStyle.Normal, fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, modifier = Modifier.padding(top = 20.dp))
            if (user.listaSeries!!.count()>=1){
                Column(modifier = Modifier
                    .padding(top = 20.dp, end = 20.dp, start = 20.dp, bottom = 30.dp)
                    .fillMaxSize()) {
                    LazyVerticalGrid(modifier = Modifier
                        .size(600.dp,620.dp),
                        verticalArrangement = Arrangement.spacedBy(35.dp),
                        columns = GridCells.Fixed(1)) {
                        items(user.listaSeries!!){
                            MyShow(show = it,uservm,navController)
                        }
                    }
                }
            }
            else{
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(text = "No tienes ninguna serie aun...",color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                }
            }
        }
    }
}