package com.example.empaq.ui.screen.chatbot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.SendChatbotRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatbotViewModel(private val repository: EmpaqRepository) : ViewModel() {

    private val _conversationId = MutableStateFlow<String?>(null)
    val conversationId: StateFlow<String?> get() = _conversationId

    private val _messages = MutableLiveData<List<ChatMessage>>()
    val messages: LiveData<List<ChatMessage>> get() = _messages

    private val firestore = FirebaseFirestore.getInstance()

    fun fetchFirstConversationId() {
        viewModelScope.launch {
            try {
                val response = repository.getConservationList()
                val firstConversationId = response.conversations.firstOrNull()?.id
                _conversationId.value = firstConversationId
            } catch (e: Exception) {
                _conversationId.value = null
            }
        }
    }

    fun sendMessage(conversationId: String, message: String) {
        viewModelScope.launch {
            try {
                repository.sendChatbotMessage(conversationId, SendChatbotRequest(message))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    init {
        _conversationId.value?.let { conversationId ->
        }
    }

    fun setFirestoreDocumentPath(documentPath: String) {
        firestore.document(documentPath).collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error == null && snapshot != null) {
                    val messageList = mutableListOf<ChatMessage>()

                    for (document in snapshot.documents) {
                        val message = document.getString("message")
                        val userUid = document.getString("uid")
                        val timestamp = document.getTimestamp("timestamp")

                        if (message != null && userUid != null && timestamp != null) {
                            messageList.add(ChatMessage(message, userUid, timestamp))
                        }
                    }

                    _messages.value = messageList
                    Log.d("Firebase", "Data berhasil diambil: ${messageList.size} pesan")
                } else {
                    Log.e("Firebase", "Error fetching messages", error)
                }
            }
    }

}