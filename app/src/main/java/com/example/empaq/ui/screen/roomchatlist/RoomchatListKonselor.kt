package com.example.empaq.ui.screen.roomchatlist

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.ConversationsItem
import com.example.empaq.data.response.SebayaItem
import com.example.empaq.data.retrofit.ApiConfig
import com.example.empaq.ui.components.itemRoomchatList
import com.example.empaq.ui.screen.ViewModelFactory
import com.example.empaq.ui.screen.sebaya.KonselorViewModel

@Composable
fun RoomchatListKonselorScreen(
    modifier: Modifier = Modifier,
    navigateToRoomchat: () -> Unit,
    roomchatUserViewModel: RoomchatUserViewModel
) {
    val viewModel: ConversationKonselorViewModel = viewModel(factory = ViewModelFactory(EmpaqRepository(ApiConfig().getApiService())))

    val conversationListState by viewModel.conversationList.observeAsState(emptyList())
    val errorState by viewModel.error.observeAsState(null)

    val viewModel2: KonselorViewModel = viewModel(factory = ViewModelFactory(EmpaqRepository(ApiConfig().getApiService())))
    val groupedKonselor by viewModel2.groupedKonselor.collectAsState()

    LazyColumn {
        items(conversationListState) { conversation ->
            val counselorName = getCounselorName(conversation, groupedKonselor)

            itemRoomchatList(
                name = counselorName,
                onClick = {
                    Log.d("CONVERSATIONid", "${conversation.id}")
                    roomchatUserViewModel.selectConversationId(conversation.id)
                    Log.d("CONVERSATION_ID", "${roomchatUserViewModel.selectedConversationId.value}")
                    navigateToRoomchat()
                }
            )
        }
    }

}

private fun getCounselorName(
    conversation: ConversationsItem,
    groupedKonselor: Map<Char?, List<SebayaItem?>>,
): String {
    if (conversation.member.size > 1) {
        val secondMember = conversation.member[2]

        groupedKonselor.forEach { (_, counselors) ->
            counselors.forEach { counselor ->
                if (counselor?.uid == secondMember) {
                    return counselor.displayName ?: ""
                }
            }
        }
    }
    return ""
}

