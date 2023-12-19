package com.example.empaq.data.response

import com.google.gson.annotations.SerializedName

data class ListConversationResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("conversations")
	val conversations: List<ConversationsItem>
)

data class ConversationsItem(

	@field:SerializedName("member")
	val member: List<String>,

	@field:SerializedName("id")
	val id: String
)