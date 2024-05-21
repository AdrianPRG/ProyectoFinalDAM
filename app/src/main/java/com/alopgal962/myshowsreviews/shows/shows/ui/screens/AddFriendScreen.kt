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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.BottomBar
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendScreen(GenericVM:GenericAndApiVM, UserVM:UserVM, navController: NavController){
    val user by UserVM.user.collectAsState()
    Scaffold (topBar = { Topbar()}, bottomBar = { BottomBar(
        onCasaClick = { navController.navigate(Routes.mainRoute.route)},
        onSeriesClick = { navController.navigate(Routes.myshowsroute.route) },
        onAmigosClick = { navController.navigate(Routes.addfriendsRoute.route) },
        onConfigClic = {navController.navigate(Routes.stadisticsRoute.route)})}) {
        Column(modifier = Modifier
            .padding(top = 110.dp, bottom = 100.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .padding(top = 20.dp)
                .size(300.dp, 50.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Button(onClick = { navController.navigate(Routes.mainRoute.route) }, colors = ButtonDefaults.buttonColors(containerColor = Color(	73, 202, 186))) {
                    Text(text = "Amigos")
                }
                Button(onClick = { navController.navigate(Routes.addfriendsRoute.route) },modifier = Modifier.padding(start = 20.dp),colors = ButtonDefaults.buttonColors(containerColor = Color(	73, 202, 186))) {
                    Text(text = "Solicitudes")
                }
            }
            Row(modifier = Modifier
                .padding(top = 20.dp)
                .size(300.dp, 100.dp)) {
                TextField(value = UserVM.amigo.value, onValueChange = {UserVM.amigo.value = it}, label = { Text(text = "Introduce el nombre de tu amigo")})
                Button(onClick = { UserVM.mandarSolicitud(UserVM.amigo.value) }) {
                    
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(color = Color(227, 214, 146))) {
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {

                }

            }
        }
    }
}