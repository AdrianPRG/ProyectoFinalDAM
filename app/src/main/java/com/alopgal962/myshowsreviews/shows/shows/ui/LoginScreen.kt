package com.alopgal962.myshowsreviews.shows.shows.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.RegisterLoginVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(registerLoginVM: RegisterLoginVM,genericVM: GenericVM ,navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(35, 54, 71))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
                .background(color = Color(232, 239, 236))
        ) {
            Text(
                text = "Iniciar sesion",
                color = Color(35, 54, 71),
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.user_signin),
                contentDescription = "Imagen Sign in"
            )
            TextField(
                value = registerLoginVM.email,
                onValueChange = {registerLoginVM.email=it},
                label = { Text(text = "Introduce tu email") },
                modifier = Modifier.padding(top = 35.dp)
            )
            TextField(
                value = registerLoginVM.password,
                onValueChange = {registerLoginVM.password=it},
                label = { Text(text = "Introduce tu password") },
                modifier = Modifier.padding(top = 35.dp)
            )
            Button(
                onClick = { registerLoginVM.iniciarsesion { navController.navigate(Routes.mainRoute.route) }
                    genericVM.obtenerPeliculas()

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(110, 149, 114)),
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 190.dp, height = 50.dp)
            ) {
                Text(text = "Iniciar Sesion", fontSize = 16.sp)
            }
            Text(text = "⚫ No tengo cuenta", color = Color.White, modifier = Modifier.padding(end = 100.dp, top = 40.dp).clickable {
                navController.navigate(Routes.registerRoute.route)
            })
        }
    }
}