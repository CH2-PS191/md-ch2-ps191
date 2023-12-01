package com.example.empaq

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun EMPAQApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Text("Hello Kotlin")
}