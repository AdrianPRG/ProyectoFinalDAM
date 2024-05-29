package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alopgal962.myshowsreviews.shows.shows.data.model.RestrictedUser
import com.alopgal962.myshowsreviews.shows.shows.data.model.User

@Composable
fun MostrarNotificacion(usuario: RestrictedUser,onAcceptClick:() -> Unit ,onDeleteclick:() -> Unit){
    Column(modifier = Modifier
        .padding(top = 30.dp)
        .background(color = Color(234, 196, 53))
        .fillMaxWidth()
        .height(150.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Image(painter = ReturnProfile(imageString = usuario.image.toString()), contentDescription = "Imagen de usuario",modifier = Modifier
                .padding(start = 10.dp)
                .size(60.dp, 60.dp)
                .clip(
                    RoundedCornerShape(40.dp)
                ))
            Text("¡${usuario.nombre} te ha enviado una solicitud de amistad!", color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, modifier = Modifier.padding(start = 15.dp))
        }
        Row(modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .height(60.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { /* TODO */},modifier = Modifier.padding(end = 15.dp).size(50.dp,50.dp).clip(
                RoundedCornerShape(20.dp)).background(color = Color(227, 214, 146))){
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir Amigo", tint = Color.Black)
            }
            IconButton(onClick = { onDeleteclick() },modifier = Modifier.padding(start = 15.dp).size(50.dp,50.dp).clip(
                RoundedCornerShape(20.dp)).background(color = Color(227, 214, 146))){
                Icon(imageVector = Icons.Default.Close, contentDescription = "Rechazar Solicitud", tint = Color.Black)
            }
        }
    }
    }