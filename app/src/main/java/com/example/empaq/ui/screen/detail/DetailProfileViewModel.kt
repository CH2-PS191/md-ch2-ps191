package com.example.empaq.ui.screen.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.UpdateProfileResponse
import kotlinx.coroutines.launch
import java.io.File

class DetailProfileViewModel(private val repository: EmpaqRepository) : ViewModel() {

    private val _updateProfileResponse = MutableLiveData<UpdateProfileResponse>()
    val updateProfileResponse: LiveData<UpdateProfileResponse> get() = _updateProfileResponse

    private val _updateFirebaseProfileResult = MutableLiveData<Boolean>()
    val updateFirebaseProfileResult: LiveData<Boolean> get() = _updateFirebaseProfileResult

    fun updateProfilePicture(file: File) {
        viewModelScope.launch {
            try {
                val response = repository.updateProfilePicture(file)
                _updateProfileResponse.value = response
                _updateFirebaseProfileResult.value = true
            } catch (e: Exception) {
                Log.d("UPLOAD", "UPLOAD FAILED: ${e.message}")
                _updateFirebaseProfileResult.value = false
            }
        }
    }

}