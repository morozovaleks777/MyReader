package com.example.myreader.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myreader.screens.details.BookDetailsScreen
import com.example.myreader.screens.home.HomeScreen
import com.example.myreader.screens.login.LoginScreen
import com.example.myreader.screens.search.SearchScreen
import com.example.myreader.screens.splash.SplashScreen
import com.example.myreader.screens.stats.StatsScreen
import com.example.myreader.screens.update.UpdateScreen

@ExperimentalComposeUiApi
@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name ){
        composable(ReaderScreens.SplashScreen.name){
            SplashScreen(navController=navController)
        }
        composable(ReaderScreens.HomeScreen.name){
            HomeScreen(navController=navController)
        }
        composable(ReaderScreens.StatsScreen.name){
            StatsScreen(navController=navController)
        }
        composable(ReaderScreens.UpdateScreen.name){
            UpdateScreen(navController=navController)
        }
        composable(ReaderScreens.DetailScreen.name){
           BookDetailsScreen(navController=navController)
        }
        composable(ReaderScreens.SearchScreen.name){
            SearchScreen(navController=navController)
        }
        composable(ReaderScreens.LoginScreen.name){
            LoginScreen(navController=navController)
        }
//        composable(ReaderScreens.CreateAccountScreen.name){
//            createAccountScreen(navController=navController)
//        }
    }
}