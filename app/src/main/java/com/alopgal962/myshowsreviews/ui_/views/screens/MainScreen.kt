package com.alopgal962.myshowsreviews.ui_.views.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.ui_.views.components.BottomBar
import com.alopgal962.myshowsreviews.ui_.views.components.Topbar
import com.alopgal962.myshowsreviews.viewmodels.RegisterLoginVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(RegisterloginVM:RegisterLoginVM,navController: NavController){
    Scaffold(topBar = { Topbar() }, bottomBar = { BottomBar({},{},{},{}) }) {
        Column(Modifier.background(color = Color(232, 239, 236))) {
            Text(text = "")
        }
    }
}