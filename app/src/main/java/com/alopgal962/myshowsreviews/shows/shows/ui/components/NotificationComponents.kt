package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.alopgal962.myshowsreviews.shows.shows.data.model.User

@Composable
fun MostrarNotificacion(usuario: User){
    Row(modifier = Modifier
        .padding(top = 30.dp)
        .fillMaxWidth()
        .height(150.dp)
        .background(color = Color(234, 196, 53)), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Image(painter = ReturnProfile(imageString = usuario.image.toString()), contentDescription = "Imagen de usuario",modifier = Modifier.size(100.dp,100.dp).clip(
            RoundedCornerShape(60.dp)))
        Text("¡El usuario ${usuario.nombre} te ha enviado una solicitud de amistad!", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif, modifier = Modifier.padding(start = 15.dp))
    }

}