package com.example.empaq.data.response

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(

	@field:SerializedName("publicUrl")
	val publicUrl: String
)
