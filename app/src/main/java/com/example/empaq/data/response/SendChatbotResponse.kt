package com.example.empaq.data.response

import com.google.gson.annotations.SerializedName

data class SendChatbotResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("response")
	val response: String? = null,

	@field:SerializedName("idresponsebot")
	val idresponsebot: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class SendChatbotRequest(
	val message: String
)
