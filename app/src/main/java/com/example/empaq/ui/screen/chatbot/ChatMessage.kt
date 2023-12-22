package com.example.empaq.ui.screen.chatbot

import com.google.firebase.Timestamp

data class ChatMessage(
    val message: String,
    val senderUid: String,
    val timestamp: Timestamp
)