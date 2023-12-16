package com.example.empaq.data.response

import com.google.gson.annotations.SerializedName

data class LoginRequest(
	val email: String,
	val password: String,
	val returnSecureToken: Boolean = true
)

data class LoginResponse(

	@field:SerializedName("captchaResponse")
	val captchaResponse: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("pendingIdToken")
	val pendingIdToken: String,

	@field:SerializedName("instanceId")
	val instanceId: String,

	@field:SerializedName("clientType")
	val clientType: String,

	@field:SerializedName("captchaChallenge")
	val captchaChallenge: String,

	@field:SerializedName("delegatedProjectNumber")
	val delegatedProjectNumber: String,

	@field:SerializedName("idToken")
	val idToken: String,

	@field:SerializedName("tenantId")
	val tenantId: String,

	@field:SerializedName("recaptchaVersion")
	val recaptchaVersion: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("returnSecureToken")
	val returnSecureToken: Boolean
)
