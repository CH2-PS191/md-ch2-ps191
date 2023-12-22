package com.example.empaq.ui.screen.roomchatlist

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.retrofit.ApiConfig
import com.example.empaq.ui.components.ChatTextField
import com.example.empaq.ui.screen.ViewModelFactory
import com.example.empaq.ui.screen.chatbot.BubbleChat
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RoomchatUserScreen(
    roomchatUserViewModel: RoomchatUserViewModel
) {
    val messages by roomchatUserViewModel.messages.observeAsState(initial = emptyList())

    val conversationId = roomchatUserViewModel.selectedConversationId.value

    LaunchedEffect(Unit) {
        roomchatUserViewModel.setFirestoreDocumentPath("conversations/$conversationId")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(Modifier.weight(1f), reverseLayout = true) {
            items(messages) { message ->
                val isUserMessage = message.senderUid == FirebaseAuth.getInstance().currentUser?.uid
                BubbleChat(message = message.message, isUserMessage)
            }

        }

        ChatTextField(onSendMessage = { message ->
            Log.d("BERHASILKAH?", "$message")
            roomchatUserViewModel.sendMessage(conversationId.toString(), message)
        }, modifier = Modifier.weight(1f))
    }

}