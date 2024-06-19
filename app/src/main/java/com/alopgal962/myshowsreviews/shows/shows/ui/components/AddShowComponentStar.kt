package com.alopgal962.myshowsreviews.shows.shows.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alopgal962.myshowsreviews.shows.shows.ui.state.ShowState
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM


@Composable
fun estrellaCalificacion(userVM: UserVM){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                for (i in 1..7) {
                    Icon(
                        imageVector =  if (i<=userVM.puntuacion.toInt()){
                            Icons.Rounded.Star }
                        else{
                            Icons.Rounded.Add
                        },
                        contentDescription = "Estrella $i",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .size(32.dp, 32.dp)
                            .clickable {
                                userVM.puntuacion = i.toString()
                            })
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(35.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                for (i in 8..10) {
                    Icon(
                        imageVector =  if (i<=userVM.puntuacion.toInt()){
                            Icons.Rounded.Star }
                        else{
                            Icons.Sharp.Add
                        },
                        contentDescription = "Estrella $i",
                        tint = Color.Yellow,
                        modifier = Modifier
                            .size(32.dp, 32.dp)
                            .clickable {
                                userVM.puntuacion = i.toString()
                            })
                }
            }
        }
}
