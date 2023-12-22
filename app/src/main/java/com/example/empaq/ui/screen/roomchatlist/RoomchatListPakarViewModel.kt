package com.example.empaq.ui.screen.roomchatlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.ConversationsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConversationPakarViewModel(private val repository: EmpaqRepository) : ViewModel() {

    private val _conversationPakarList = MutableLiveData<List<ConversationsItem>>()
    val conversationPakarList: LiveData<List<ConversationsItem>> get() = _conversationPakarList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        // Call the function to fetch conversation list in the constructor
        getConversationList()
    }

    private fun getConversationList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getConservationPakarList()
                Log.d("NARUTOTOTOT", "${response.conversations}")
                if (response.success) {
                    // Filter conversations with member count greater than three
                    val filteredConversations = response.conversations
                    _conversationPakarList.postValue(filteredConversations)
                } else {
                    _error.postValue("Failed to fetch conversation list")
                }
            } catch (e: Exception) {
                _error.postValue("An error occurred: ${e.message}")
            }
        }
    }
}