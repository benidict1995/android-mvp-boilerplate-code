package com.benidict.data.model.response

import com.google.gson.annotations.SerializedName

data class ContentResponse<T>(
    @SerializedName("status") val status: String?= null,
    @SerializedName("totalResults") val totalResults:String?= null,
    @SerializedName("articles") val articles: List<T>?= null
)