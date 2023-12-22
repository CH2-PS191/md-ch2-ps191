package com.example.empaq.data.response

import com.google.gson.annotations.SerializedName

data class SendUserResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("message")
	val message: String
)
data class SendUserRequest(
	val message: String
)

