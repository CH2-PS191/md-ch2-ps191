package com.example.empaq.ui.screen.roomchatlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.SendUserRequest
import com.example.empaq.ui.screen.chatbot.ChatMessage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.launch

class RoomchatUserViewModel(private val repository: EmpaqRepository) : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    private val _selectedConversationId = MutableLiveData<String?>(null)
    val selectedConversationId : LiveData<String?> get() = _selectedConversationId

    private val _messages = MutableLiveData<List<ChatMessage>>()
    val messages: LiveData<List<ChatMessage>> get() = _messages

    fun selectConversationId(conversationId: String) {
        _selectedConversationId.value = conversationId
        Log.d("APAHASIL", "${selectedConversationId.value}")
    }

    fun setFirestoreDocumentPath(documentPath: String) {
        // Mendengarkan perubahan pada dokumen tertentu di Firestore
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

    fun sendMessage(conversationId: String, message: String) {
        viewModelScope.launch {
            try {
                repository.sendUserMessage(conversationId, SendUserRequest(message))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

