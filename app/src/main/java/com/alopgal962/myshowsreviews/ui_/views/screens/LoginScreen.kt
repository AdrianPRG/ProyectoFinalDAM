package com.alopgal962.myshowsreviews.ui_.views.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.R
import com.alopgal962.myshowsreviews.ui_.views.components.BottomBar
import com.alopgal962.myshowsreviews.ui_.views.components.Topbar
import com.alopgal962.myshowsreviews.viewmodels.RegisterLoginVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(registerLoginVM:RegisterLoginVM,navController: NavController){
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(35, 54, 71))) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
            .background(color = Color(232, 239, 236))) {
            Text(text = "Iniciar sesion", color = Color(35, 54, 71), fontSize = 32.sp, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 30.dp)) {
            Image(modifier = Modifier.size(80.dp),painter = painterResource(id = R.drawable.user_signin), contentDescription = "Imagen Sign in")
            TextField(value = "", onValueChange ={} , label = { Text(text = "Introduce tu email")}, modifier = Modifier.padding(top = 20.dp))
            TextField(value = "", onValueChange ={} , label = { Text(text = "Introduce tu password")}, modifier = Modifier.padding(top = 20.dp))
        }
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().height(200.dp) ) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = 20.dp)) {
                Text(text = "aaaaaaaaaaaaaa")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "aaaaaaaaaaaaa")
            }
        }
    }
}