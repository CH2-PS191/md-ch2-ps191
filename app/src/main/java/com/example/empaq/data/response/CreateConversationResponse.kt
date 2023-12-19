package com.example.empaq.data.response

import com.google.gson.annotations.SerializedName

data class CreateConversationResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("message")
	val message: String
)