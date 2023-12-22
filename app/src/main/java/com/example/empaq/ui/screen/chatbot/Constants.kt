package com.example.empaq.ui.screen.chatbot

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp

object Constants {
    const val TAG = "EMPAQ"

    const val CONVERSATIONS = "conversations"
    const val CONVERSATION = "conversation"
    const val TIMESTAMP = "timestamp"
    const val IS_CURRENT_USER = "is_current_user"
}

data class ChatMessage(
    val message: String,
    val senderUid: String,
    val timestamp: Timestamp
)

//@Composable
//fun SingleMessage(message: String, isCurrentUser: Boolean) {
//    Card(
//        shape = RoundedCornerShape(16.dp),
//    ) {
//        Text(
//            text = message,
//            textAlign =
//            if (isCurrentUser)
//                TextAlign.End
//            else
//                TextAlign.Start,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            color = if (!isCurrentUser) MaterialTheme.colorScheme.primary else Color.White
//        )
//    }
//}
//
//@Preview
//@Composable
//fun singeMessagePreview() {
//    SingleMessage(message = "DICOBA GES", isCurrentUser = false)
//}