package com.alopgal962.myshowsreviews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alopgal962.myshowsreviews.shows.shows.data.model.Routes
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.StatisticsScreen
import com.alopgal962.myshowsreviews.ui.theme.MyShowsReviewsTheme
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.LoginScreen
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.MainScreen
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.RegisterScreen
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.ShowInformation
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.StatisticsScreen
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.RegisterLoginVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyShowsReviewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val RegisterLoginVM: RegisterLoginVM by viewModels()
                    val navController = rememberNavController()
                    val GenericVM: GenericVM by viewModels()
                    NavHost(navController = navController, startDestination = Routes.loginRoute.route ){
                        composable(Routes.registerRoute.route){ RegisterScreen(registerLoginVM = RegisterLoginVM, navController) }
                        composable(Routes.mainRoute.route) { MainScreen(RegisterloginVM = RegisterLoginVM, GenericVM = GenericVM ,navController = navController) }
                        composable(Routes.loginRoute.route) { LoginScreen(registerLoginVM = RegisterLoginVM, genericVM = GenericVM(), navController = navController) }
                        composable(Routes.stadisticsRoute.route) { StatisticsScreen( RegisterLoginVM = RegisterLoginVM, GenericVM = GenericVM, navController = navController ) }
                        composable("ShowInformation/{name}", arguments = listOf(navArgument("name"){type = NavType.StringType})) {
                            val nombre = it.arguments?.getString("name") ?: "Godzilla Minus-One"
                            ShowInformation(GenericVM = GenericVM,navController = navController) }
                    }
                }
            }
        }
    }
}
