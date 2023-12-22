package com.example.empaq.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Chatbot : Screen("chatbot")
    object Profile : Screen("profile")
    object DetailProfile : Screen("profile/detail")
    object Specialist : Screen("specialist")
    object Konselor : Screen("konselor")
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot_password")
    object Main : Screen("main")
    object ConversationsKonselor : Screen("konselor/conversationKonselor")
    object ConversationsPakar : Screen("Pakar/conversationPakar")
    object RoomchatUser : Screen("conversations/roomchat")
    object HelpnSupport : Screen("profile/HelpnSupport")
    object About : Screen("profile/About")
}
