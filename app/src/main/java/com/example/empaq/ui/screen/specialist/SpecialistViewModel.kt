package com.example.empaq.ui.screen.specialist

import androidx.lifecycle.ViewModel
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.model.Specialist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SpecialistViewModel(private val repository: EmpaqRepository) : ViewModel() {
    private val _groupedSpecialist = MutableStateFlow(
        repository.getSpecialist()
            .sortedBy { it.nama }
            .groupBy { it.nama[0] }
    )
    val groupedSpecialist: StateFlow<Map<Char, List<Specialist>>> get() =_groupedSpecialist
}