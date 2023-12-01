package com.example.empaq.data

class EmpaqRepository {

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