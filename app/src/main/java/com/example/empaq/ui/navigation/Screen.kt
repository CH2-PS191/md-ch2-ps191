package com.example.empaq.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chatbot : Screen("chatbot")
    object Profile : Screen("profile")
    object DetailProfile : Screen("detail")

}
