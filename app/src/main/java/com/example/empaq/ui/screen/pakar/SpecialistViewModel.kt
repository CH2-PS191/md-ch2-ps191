package com.example.empaq.ui.screen.pakar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.PakarItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpecialistViewModel(private val repository: EmpaqRepository) : ViewModel() {
//    private val _groupedSpecialist = MutableStateFlow(
//        repository.getSpecialist()
//            .sortedBy { it.nama }
//            .groupBy { it.nama[0] }
//    )
//    val groupedSpecialist: StateFlow<Map<Char, List<Specialist>>> get() =_groupedSpecialist

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

//            groupBy { it?.displayName?.firstOrNull()?.uppercaseChar() }
//                ?: emptyMap()

            _groupedSpecialist.value = grouped!!.toMap()
        } catch (e: Exception) {
            // Handle error
            e.printStackTrace()
        }
    }
}