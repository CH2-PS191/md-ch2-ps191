package com.example.empaq.ui.screen.chatbot

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.retrofit.ApiConfig
import com.example.empaq.ui.components.ChatTextField
import com.example.empaq.ui.screen.ViewModelFactory
import com.example.empaq.ui.theme.Bluedish
import com.example.empaq.ui.theme.bluefeather
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ChatbotScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: ChatbotViewModel = viewModel(factory = ViewModelFactory(EmpaqRepository(ApiConfig().getApiService())))
    val conversationId by viewModel.conversationId.collectAsState()
    val messages by viewModel.messages.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchFirstConversationId()
    }

    LaunchedEffect(conversationId) {
        conversationId?.let { convoId ->
            viewModel.setFirestoreDocumentPath("conversations/$convoId")
        }
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
            conversationId?.let {convoId ->
                Log.d("BERHASILKAH?", "$message")
                viewModel.sendMessage(convoId, message)
            }
        }, modifier = Modifier.weight(1f))
    }
}

@Composable
fun BubbleChat(
    message: String,
    isUserMessage: Boolean
) {
    val bubbleColor = if (isUserMessage) Color.LightGray else bluefeather
    val bubbleAlignment = if (isUserMessage) Alignment.End else Alignment.Start
    val bubbleTextColor = if (isUserMessage) Color.Black else Color.Black

    Box(modifier = Modifier.fillMaxWidth().wrapContentWidth(bubbleAlignment)){
        Text(
            text = message,
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(bubbleColor)
                .widthIn(0.dp, 300.dp)
                .padding(13.dp),
            fontSize = 12.sp,
            color = bubbleTextColor
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun BubblechatPreview() {
    Column {
        BubbleChat("DICOBA GES", true)
        BubbleChat("JADI KALAU MISALNYA KEK MAUKA JADI PANJANG SKALI BEMNAMI GES?", false)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChatbotScreenPreview() {
    ChatbotScreen()
}