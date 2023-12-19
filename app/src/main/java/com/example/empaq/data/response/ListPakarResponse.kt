package com.example.empaq.data.response

import com.google.gson.annotations.SerializedName

data class ListPakarResponse(

	@field:SerializedName("pakar")
	val pakar: List<PakarItem?>? = null
)

data class ProviderDataItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("providerId")
	val providerId: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class Metadata(

	@field:SerializedName("lastSignInTime")
	val lastSignInTime: String? = null,

	@field:SerializedName("creationTime")
	val creationTime: String? = null,

	@field:SerializedName("lastRefreshTime")
	val lastRefreshTime: String? = null
)

data class PakarItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("emailVerified")
	val emailVerified: Boolean? = null,

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("providerData")
	val providerData: List<ProviderDataItem?>? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("disabled")
	val disabled: Boolean? = null,

	@field:SerializedName("passwordSalt")
	val passwordSalt: String? = null,

	@field:SerializedName("tokensValidAfterTime")
	val tokensValidAfterTime: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("passwordHash")
	val passwordHash: String? = null,

	@field:SerializedName("customClaims")
	val customClaims: CustomClaims? = null
)

data class CustomClaims(

	@field:SerializedName("pakar")
	val pakar: Boolean? = null
)

data class ListSebayaResponse(

	@field:SerializedName("sebaya")
	val sebaya: List<SebayaItem?>? = null
)

data class SebayaItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("emailVerified")
	val emailVerified: Boolean? = null,

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("providerData")
	val providerData: List<ProviderDataItem?>? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("disabled")
	val disabled: Boolean? = null,

	@field:SerializedName("passwordSalt")
	val passwordSalt: String? = null,

	@field:SerializedName("tokensValidAfterTime")
	val tokensValidAfterTime: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("passwordHash")
	val passwordHash: String? = null,

	@field:SerializedName("customClaims")
	val customClaims: CustomClaims? = null
)
