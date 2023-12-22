package com.example.empaq.ui.screen.detail

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.response.UpdateProfileResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
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
//                val defaultPhoto = UserProfileChangeRequest.Builder()
//                    .setPhotoUri(Uri.parse(_updateProfileResponse.toString()))
//                    .build()
//                FirebaseAuth.getInstance().currentUser?.updateProfile(defaultPhoto)?.addOnCompleteListener { updateTask ->
//                    if (updateTask.isSuccessful) {
//                        Log.d("UPDATE-REGIST", "REGISTER UPDATE SUCCESS")
//                    } else {
//                        Log.d("UPDATE-REGIST", "REGISTER UPDATE FAILED")
//                    }
//                }
                _updateFirebaseProfileResult.value = true
            } catch (e: Exception) {
                Log.d("UPLOAD", "UPLOAD FAILED: ${e.message}")
                _updateFirebaseProfileResult.value = false
            }
        }
    }

}