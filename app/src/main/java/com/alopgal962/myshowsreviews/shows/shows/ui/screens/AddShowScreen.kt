package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddShow(userVM: UserVM,navController: NavController){
    val showInsertar by userVM.showInsertar.collectAsState()
    Scaffold(topBar = { Topbar()}) {
        Column(modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxSize()
            .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                    Column(modifier = Modifier.size(350.dp,100.dp).clip(RoundedCornerShape(10.dp)).border(width = 5.dp, color = Color.Black), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(text = "Show a calificar", color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold, fontSize = 20.sp )
                        Text(text = showInsertar.titulo.toString(),modifier = Modifier.padding(top = 10.dp),color = Color.Black, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 13.5.sp )
            }
        }
    }
}
}