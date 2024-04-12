package com.alopgal962.myshowsreviews.ui_.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alopgal962.myshowsreviews.R

@Preview(showBackground = true)
@Composable
fun Topbar(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(110.dp)
        .background(color = Color(33, 93, 46)), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.iconoapp), contentDescription = "ImagenIcono", modifier = Modifier.padding(start = 18.dp).size(90.dp,110.dp))
        Text(text = "MyShowsReviews", fontSize = 28.sp, fontWeight = FontWeight.SemiBold, color = Color.White, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif, modifier = Modifier.padding(start = 18.dp))
    }
}
