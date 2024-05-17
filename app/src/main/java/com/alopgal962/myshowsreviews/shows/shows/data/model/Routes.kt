package com.alopgal962.myshowsreviews.shows.shows.data.model

sealed class Routes(val route:String){
    object registerRoute: Routes("RegisterScreen")
    object loginRoute: Routes("LoginScreen")
    object mainRoute: Routes("MainScreen")
    object stadisticsRoute:Routes("Statistics")
    object myshowsroute:Routes("showsScreen")
    object friendsRoute:Routes("friendsScreen")
    object showRoute:Routes("ShowInformation/{nombre}")
}
