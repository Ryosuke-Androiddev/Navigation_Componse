package com.example.navigationcomponent.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "Ryosuke"
                    nullable = true
                }
            )
        ){ parameter ->
            DetailScreen(name = parameter.arguments?.getString("name"))
        }
    }
}


@Composable
fun MainScreen(navController: NavController){

    var text by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        TextField(
            value = text,
            label = {
                Text(text = "Enter your name")
            },
            onValueChange = {
                text = it
            },
            singleLine = true,
            modifier =  Modifier.fillMaxWidth(0.8f).fillMaxHeight(0.08f)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // you can not pass your parameter with this section so,you have to make another function for passing parameters!
        Button(
            onClick = {
            navController.navigate(Screen.DetailScreen.withArgs(text))
        }
        ) {
            Text(text = "To DetailScreen")
        }
    }
}

@Composable
fun DetailScreen(name: String?){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "My name is $name")
    }
}