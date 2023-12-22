package com.example.empaq.data.response

import com.google.gson.annotations.SerializedName

data class AddSpecialistResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)
data class AddSpecialistRequest(
	val pakarUid : String
)
