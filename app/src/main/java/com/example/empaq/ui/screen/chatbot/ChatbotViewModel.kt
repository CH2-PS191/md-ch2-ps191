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
//            val response = repository.getConservationList()
//            if (response.success == true) {
//                _conversationId.value = response.conversations.first().id
//            } else {
//
//            }
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
        // Mendengarkan perubahan pada koleksi pesan di Firestore
        _conversationId.value?.let { conversationId ->
//            firestore.collection("conversations").document("mi4NJk5nheQwnOfqvQDB")
//                .collection("messages")
//                .addSnapshotListener { snapshot, error ->
//                    if (error == null && snapshot != null) {
//                        val messageList = mutableListOf<String>()
//
//                        for (document in snapshot.documents) {
//                            val message = document.getString("message")
//                            message?.let {
//                                messageList.add(it)
//                            }
//                        }
//
//                        _messages.value = messageList
//                        Log.d("Firebase", "Data berhasil diambil: ${messageList.size} pesan")
//                    } else {
//                        Log.d("Firebase", "Snapshot kosong")
//                    }
//                }

//            firestore.collection("conversations")
//                .whereEqualTo(conversationId, conversationId)
//                .addSnapshotListener { value, e ->
//                    if (e != null) {
//                        return@addSnapshotListener
//                    }
//
//                    val messages = ArrayList<String>()
//                    for (doc in value!!) {
//                        doc.getString("messages").let {
//                            messages.add(it.toString())
//                        }
//                    }
//
//                    _messages.value = messages
//                }

        }
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
//                        message?.let {
//                            messageList.add(it)
//                        }
                    }

                    _messages.value = messageList
                    Log.d("Firebase", "Data berhasil diambil: ${messageList.size} pesan")
                } else {
                    Log.e("Firebase", "Error fetching messages", error)
                }
            }
    }

}