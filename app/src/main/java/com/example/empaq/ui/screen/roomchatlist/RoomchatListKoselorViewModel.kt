package com.example.empaq.ui.screen.roomchatlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.ConversationsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConversationKonselorViewModel(private val repository: EmpaqRepository) : ViewModel() {

    private val _conversationList = MutableLiveData<List<ConversationsItem>>()
    val conversationList: LiveData<List<ConversationsItem>> get() = _conversationList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getConversationList()
    }

    private fun getConversationList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getConservationSebayaList()
                if (response.success) {
                    val filteredConversations = response.conversations.filter { it.member.size > 2 }
                    _conversationList.postValue(filteredConversations)
                } else {
                    _error.postValue("Failed to fetch conversation list")
                }
            } catch (e: Exception) {
                _error.postValue("An error occurred: ${e.message}")
            }
        }
    }
}