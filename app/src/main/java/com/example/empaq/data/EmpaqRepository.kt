package com.example.empaq.data

import com.example.empaq.model.Specialist
import com.example.empaq.model.SpecialistDataResourceDummy

class EmpaqRepository {

    fun getSpecialist(): List<Specialist> {
        return SpecialistDataResourceDummy.SpecialistList
    }

    companion object {
        @Volatile
        private var instance: EmpaqRepository? = null

        fun getInstance(): EmpaqRepository =
            instance ?: synchronized(this) {
                EmpaqRepository().apply {
                    instance = this
                }
            }
    }
}