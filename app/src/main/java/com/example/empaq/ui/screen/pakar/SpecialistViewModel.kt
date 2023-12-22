package com.example.empaq.ui.screen.pakar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.AddSpecialistRequest
import com.example.empaq.data.response.PakarItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpecialistViewModel(private val repository: EmpaqRepository) : ViewModel() {

    private val _groupedSpecialist = MutableStateFlow<Map<Char?, List<PakarItem?>>>(emptyMap())
    val groupedSpecialist: StateFlow<Map<Char?, List<PakarItem?>>> get() = _groupedSpecialist

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        try {
            val pakarList = repository.getPakarList().pakar
            val grouped = pakarList?.sortedBy { it?.displayName }?.groupBy { it?.displayName!![0] }

            _groupedSpecialist.value = grouped!!.toMap()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun createConversationAndAddSpecialist(pakarUid: String) {
        viewModelScope.launch {
            try {
                val createConversationResponse = repository.createConversation()
                if (createConversationResponse.success) {
                    val conversationId = createConversationResponse.id
                    val pakarUid = AddSpecialistRequest(pakarUid)
                    val addSpecialistResponse = repository.updateSpecialist(conversationId, pakarUid)
                    if (addSpecialistResponse.success) {
                        Log.d("SPECIALIST", "${addSpecialistResponse.message}")
                    } else {
                        Log.d("SPECIALIST", "${addSpecialistResponse.message}")
                    }
                } else {
                    Log.d("SPECIALIST", "${createConversationResponse.message}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}