package com.example.empaq.ui.screen.sebaya

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.AddSpecialistRequest
import com.example.empaq.data.response.SebayaItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KonselorViewModel(private val repository: EmpaqRepository) : ViewModel() {
//    private val _groupedSpecialist = MutableStateFlow(
//        repository.getSpecialist()
//            .sortedBy { it.nama }
//            .groupBy { it.nama[0] }
//    )
//    val groupedSpecialist: StateFlow<Map<Char, List<Specialist>>> get() =_groupedSpecialist

    private val _groupedKonselor = MutableStateFlow<Map<Char?, List<SebayaItem?>>>(emptyMap())
    val groupedKonselor: StateFlow<Map<Char?, List<SebayaItem?>>> get() = _groupedKonselor

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        try {
            val konselorList = repository.getKonselorList().sebaya
            val grouped = konselorList?.sortedBy { it?.displayName }?.groupBy { it?.displayName!![0] }

//            groupBy { it?.displayName?.firstOrNull()?.uppercaseChar() }
//                ?: emptyMap()

            _groupedKonselor.value = grouped!!.toMap()
        } catch (e: Exception) {
            // Handle error
            e.printStackTrace()
        }
    }

    fun createConversationAndAddKonselor(pakarUid: String) {
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
                // Handle error
                e.printStackTrace()
            }
        }
    }
}