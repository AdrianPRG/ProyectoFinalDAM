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
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.DiscoverPeople
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.AddShow
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.StatisticsScreen
import com.alopgal962.myshowsreviews.ui.theme.MyShowsReviewsTheme
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.LoginScreen
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.MainScreen
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.MyFullShowScreen
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.MyShowsScreen
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.RegisterScreen
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.ScreenSerieUsuario
import com.alopgal962.myshowsreviews.shows.shows.ui.screens.ShowExplained
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.GenericAndApiVM
import com.alopgal962.myshowsreviews.shows.shows.viewmodels.UserVM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            MyShowsReviewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val uservm: UserVM by viewModels()
                    val navController = rememberNavController()
                    val genericandapivm: GenericAndApiVM by viewModels()
                    NavHost(navController = navController, startDestination = Routes.loginRoute.route ){
                        composable(Routes.registerRoute.route){ RegisterScreen(userVM = uservm, navController) }
                        composable(Routes.mainRoute.route) { MainScreen(UserVM = uservm, GenericAndApiVM = genericandapivm ,navController = navController) }
                        composable(Routes.loginRoute.route) { LoginScreen(userVM = uservm, genericAndApiVM = GenericAndApiVM(), navController = navController) }
                        composable(Routes.stadisticsRoute.route) { StatisticsScreen( UserVM = uservm, GenericAndApiVM = genericandapivm, navController = navController ) }
                        composable(Routes.addfriendsRoute.route) { DiscoverPeople(GenericVM = genericandapivm, uservm =uservm , navController = navController)  }
                        composable(Routes.myshowsroute.route){ MyShowsScreen(uservm = uservm, genericAndApiVM =genericandapivm , navController = navController) }
                        composable("ShowExplained/{name}", arguments = listOf(navArgument("name"){type = NavType.StringType})) {
                            ShowExplained(GenericAndApiVM = genericandapivm,navController = navController) }
                        composable("AddShow/{name}", arguments = listOf(navArgument("name"){type = NavType.StringType})) {
                            AddShow(userVM = uservm,navController = navController) }
                        composable("MyShowExplained/{name}", arguments = listOf(navArgument("name"){type = NavType.StringType})){
                            MyFullShowScreen(userVM = uservm, navController =  navController)
                        }
                        composable ("UserShowExplained/{name}", arguments = listOf(navArgument("name"){type = NavType.StringType})){
                            ScreenSerieUsuario(userVM = uservm, navController = navController )
                        }
                    }
                }
            }
        }
    }
}
