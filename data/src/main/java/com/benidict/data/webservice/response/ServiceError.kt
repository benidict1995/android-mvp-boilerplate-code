package com.benidict.data.webservice.response

import com.google.gson.annotations.SerializedName

data class  ServiceError(
    val description: String,
    val code: String,
    @SerializedName("message") val message: String
)