package com.rockmvvm.rockbasemvvm.data

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Generated("com.robohorse.robopojogenerator")
data class ResponseDTO(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("terms")
	val terms: String? = null,

	@field:SerializedName("privacy")
	val privacy: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("quotes")
	val quotes: QuotesDTO? = null
)