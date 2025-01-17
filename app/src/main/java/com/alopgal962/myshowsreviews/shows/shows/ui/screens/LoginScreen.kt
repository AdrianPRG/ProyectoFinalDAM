package com.alopgal962.myshowsreviews.shows.shows.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.R
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(userVM: UserVM, genericAndApiVM: GenericAndApiVM, navController: NavController) {
    var context = LocalContext.current.applicationContext
    //Pantalla que ocupa todo el tamaño
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(35, 54, 71))
    ) {
        //Columna con texto de inicio de sesion
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
            //Textfield para el campo de el email
            TextField(
                value = userVM.emaiLRegisterLogin,
                onValueChange = {userVM.emaiLRegisterLogin=it},
                label = { Text(text = "Introduce tu email",color = Color(35, 54, 71)) },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.padding(top = 35.dp).width(275.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(232, 239, 236), textColor = Color(35, 54, 71))
            )
            //Textfield para el campo de la contraseña
            TextField(
                value = userVM.passwordRegisterLogin,
                onValueChange = {userVM.passwordRegisterLogin=it},
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(text = "Introduce tu contraseña", color = Color(35, 54, 71)) },
                modifier = Modifier.padding(top = 35.dp).width(275.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(232, 239, 236), textColor = Color(35, 54, 71))

            )
            //Boton que al pulsar inicia sesion, navega hacia la pantalla principal.
            //Obtiene las peliculas, recupera las serie del usuario actual si es que tiene, y obtiene la lista de todos los usuarios
            //Por asi decirlo, inicializa el contenido de muchas pantallas
            Button(
                onClick = { userVM.iniciarsesion({navController.navigate(Routes.mainRoute.route) },context)
                    userVM.obtenerListaUsuarios()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(110, 149, 114)),
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(width = 190.dp, height = 50.dp)
            ) {
                Text(text = "Iniciar Sesion", color = Color.White, fontSize = 16.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.SemiBold)
            }
            //Si no tiene cuenta, al pulsar sobre el texto se navegará a la pantalla de registro.
            //Se borran los campos de login
            Text(text = "⚫ No tengo cuenta", color = Color.White, fontFamily = FontFamily.Serif, modifier = Modifier
                .padding(end = 100.dp, top = 40.dp)
                .clickable {
                    navController.navigate(Routes.registerRoute.route)
                    userVM.borrarCamposRegistroLogin()
                })
        }
    }
}