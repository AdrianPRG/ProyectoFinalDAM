package com.alopgal962.myshowsreviews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.ViewModel
import com.alopgal962.myshowsreviews.model.Routes
import com.alopgal962.myshowsreviews.ui.theme.MyShowsReviewsTheme
import com.alopgal962.myshowsreviews.ui_.views.screens.LoginScreen
import com.alopgal962.myshowsreviews.ui_.views.screens.MainScreen
import com.alopgal962.myshowsreviews.ui_.views.screens.RegisterScreen
import com.alopgal962.myshowsreviews.viewmodels.RegisterLoginVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyShowsReviewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val viewmodels:RegisterLoginVM by viewModels()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.registerRoute.route ){
                        composable(Routes.registerRoute.route){ RegisterScreen(registerLoginVM = viewmodels, navController)}
                        composable(Routes.mainRoute.route) { MainScreen(RegisterloginVM = viewmodels, navController = navController) }
                        composable(Routes.loginRoute.route) { LoginScreen(
                            registerLoginVM = viewmodels,
                            navController = navController
                        ) }
                    }
                }
            }
        }
    }
}
