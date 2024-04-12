package com.alopgal962.myshowsreviews.model

sealed class Routes(val route:String){
    object registerRoute:Routes("RegisterScreen")
    object loginRoute:Routes("LoginScreen")
    object mainRoute:Routes("MainScreen")

}
