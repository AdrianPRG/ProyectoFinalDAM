package com.alopgal962.myshowsreviews.shows.shows.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.myshowsreviews.R
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(userVM: UserVM, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(232, 239, 236)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(bottomEnd = 180.dp, bottomStart = 180.dp))
                .background(color = Color(35, 54, 71)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Registrarse",
                color = Color.White,
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        }
        Text(
            text = "¡Elige tu Avatar!",
            fontSize = 23.sp,
            color = Color(35, 54, 71),
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(top = 20.dp)
        )
        //Row que contiene las imagenes
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.perfil1),
                contentDescription = "photo1",
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(90.dp)
                    .clip(RoundedCornerShape(40))
                    .clickable { userVM.imagenRegister = "perfil1" }
            )
            Image(
                painter = painterResource(id = R.drawable.perfil2),
                contentDescription = "photo2",
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(90.dp)
                    .clip(RoundedCornerShape(40))
                    .clickable { userVM.imagenRegister = "perfil2" }
            )
            Image(
                painter = painterResource(id = R.drawable.perfil3),
                contentDescription = "photo3",
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(40))
                    .clickable { userVM.imagenRegister = "perfil3" }
            )
        }
        //Columna que contiene las rows de cada textField
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            //TextField 1 e Imagen
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding()
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.r_name1),
                    contentDescription = "Nombre",
                    modifier = Modifier
                        .size(65.dp)
                        .padding(end = 10.dp)
                )
                TextField(
                    value = userVM.nombreRegister,
                    singleLine = true,
                    onValueChange = { if (userVM.nombreRegister.length<=10){

                        userVM.nombreRegister = it }
                        else{
                            userVM.nombreRegister = it
                    }
                                                                           },
                    label = { Text(text = "Nombre de usuario", color = Color.White) },
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(35, 54, 71),
                        textColor = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = "Inserta tu nombre de usuario", color = Color.White
                        )
                    },
                    modifier = Modifier
                        .padding(end = 20.dp, bottom = 20.dp)
                        .size(260.dp, 60.dp)
                )
            }
            //TextField 2 e Imagen
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.r_email2),
                    contentDescription = "Email",
                    modifier = Modifier
                        .size(65.dp)
                        .padding(end = 10.dp)
                )
                TextField(
                    value = userVM.emaiLRegisterLogin,
                    onValueChange = { userVM.emaiLRegisterLogin = it },
                    singleLine = true,
                    label = { Text(text = "Correo electronico", color = Color.White) },
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(35, 54, 71),
                        textColor = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = "Inserta tu correo electronico", color = Color.White
                        )
                    },
                    modifier = Modifier
                        .padding(end = 20.dp, bottom = 20.dp)
                        .size(260.dp, 60.dp)
                )
            }
            //TextField 3 e Imagen
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.r_pass3),
                    contentDescription = "Contraseña",
                    modifier = Modifier
                        .size(65.dp)
                        .padding(end = 10.dp)
                )
                TextField(
                    value = userVM.passwordRegisterLogin,
                    onValueChange = { userVM.passwordRegisterLogin = it },
                    label = { Text(text = "Contraseña", color = Color.White) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(35, 54, 71),
                        textColor = Color.White
                    ),
                    placeholder = {
                        Text(
                            text = "Inserta tu contraseña", color = Color.White
                        )
                    },
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(260.dp, 60.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)

            ) {
                Button(
                    onClick = {
                        userVM.registrarme { navController.navigate(Routes.loginRoute.route) } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(110, 149, 114)),
                    modifier = Modifier
                        .padding(end = 40.dp)
                        .size(width = 130.dp, height = 50.dp)
                ) {
                    Text(text = "Registrarme")
                }
                Button(
                    onClick = { userVM.borrarCampos() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(210, 120, 120)),
                    modifier = Modifier.size(width = 130.dp, height = 50.dp)
                ) {
                    Text(text = "Cancelar")
                }
            }
            Text(
                text = "⚫ Ya tengo una cuenta",
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(end = 120.dp, bottom = 30.dp).clickable {
                    navController.navigate(Routes.loginRoute.route)
                    userVM.borrarCampos()
                })
        }
    }
}