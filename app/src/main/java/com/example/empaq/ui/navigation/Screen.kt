package com.example.empaq.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chatbot : Screen("chatbot")
    object Profile : Screen("profile")
    object DetailProfile : Screen("profile/detail")
    object Specialist : Screen("specialist")
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot_password")
    object Main : Screen("main")
}
