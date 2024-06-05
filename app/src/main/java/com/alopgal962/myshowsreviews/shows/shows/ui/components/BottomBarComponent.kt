package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alopgal962.myshowsreviews.R

@Composable
fun BottomBar(onCasaClick:() -> Unit, onSeriesClick:() -> Unit, onAmigosClick:() -> Unit, onConfigClic:() -> Unit ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color = Color(33, 93, 46)), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.i_casa_1),   contentDescription =  "Casa", modifier = Modifier.size(100.dp,70.dp).padding(start = 5.dp,end = 13.dp).clickable { onCasaClick() })
        Image(painter = painterResource(id = R.drawable.i_serie_2), contentDescription =  "Series", modifier = Modifier.size(100.dp,70.dp).padding(start = 5.dp,end = 13.dp).clickable { onSeriesClick() })
        Image(painter = painterResource(id = R.drawable.i_amigos_3),contentDescription =  "Amigos", modifier = Modifier.size(100.dp,70.dp).padding(start = 5.dp,end = 13.dp).clickable { onAmigosClick() })
        Image(painter = painterResource(id = R.drawable.i_config_4),contentDescription =  "Configuracion", modifier = Modifier.size(100.dp,70.dp).padding(start = 5.dp,end = 13.dp).clickable { onConfigClic() })
    }
}