package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.BottomBar
import com.alopgal962.myshowsreviews.shows.shows.ui.components.MostrarNotificacion
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
        onAmigosClick = { },
        onConfigClic = {
            navController.navigate(Routes.stadisticsRoute.route)
        })}) {
        Column(modifier = Modifier
            .padding(top = 110.dp, bottom = 100.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
                BadgedBox(modifier = Modifier.padding(top = 30.dp),badge = { Badge(containerColor = Color.Green, modifier = Modifier
                    .padding(top = 20.dp, end = 10.dp)
                    .size(25.dp, 20.dp)){ Text(text = user.listaPeticiones!!.count().toString(), color = Color.Black)} }) {
                    OutlinedButton(onClick = { UserVM.ObtenerSolicitudesAmistad() }, border = BorderStroke(2.dp, color = Color.Black),colors = ButtonDefaults.buttonColors(containerColor = Color(	72, 119, 194))) {
                        Text(text = "Solicitudes", color = Color.White, fontStyle = FontStyle.Normal, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
                    }
                }
            Row(modifier = Modifier
                .padding(top = 20.dp)
                .size(320.dp, 100.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                TextField(
                    //textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    shape = RoundedCornerShape(20.dp),
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White, textColor = Color.Black),
                    trailingIcon = { Icon(imageVector = Icons.Default.Send, contentDescription = "", tint = Color.Black, modifier = Modifier
                        .size(30.dp, 30.dp)
                        .clickable { UserVM.mandarSolicitud(UserVM.amigo.value) }) },
                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon", tint = Color.Black)},
                    modifier = Modifier
                    .clip(RoundedCornerShape(15.dp)),value = UserVM.amigo.value, onValueChange = {UserVM.amigo.value = it}, label = { Text(text = "Introduce correo de amigo", color = Color.Black, fontSize = 13.sp)})
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(color = Color(227, 214, 146)), horizontalAlignment = Alignment.CenterHorizontally) {
                LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 400.dp), modifier = Modifier.fillMaxWidth().height(400.dp)) {
                    items(user.listaPeticiones!!){
                        MostrarNotificacion(usuario = it)
                    }
                }
            }
        }
    }
}