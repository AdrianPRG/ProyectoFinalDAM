package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.components.BottomBar
import com.alopgal962.myshowsreviews.shows.shows.ui.components.Topbar
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyShowsScreen(genericvm:GenericAndApiVM, UserVM:UserVM, navController: NavController){
    Scaffold(topBar = { Topbar() }, bottomBar = { BottomBar(
        onCasaClick = { navController.navigate(Routes.mainRoute.route) },
        onSeriesClick = { /*TODO*/ },
        onAmigosClick = { /*TODO*/ },
        onConfigClic = {navController.navigate(Routes.stadisticsRoute.route)} )
    }) {
        Column {

        }
    }
}