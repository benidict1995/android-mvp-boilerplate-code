package com.benidict.data.webservice

import com.benidict.data.model.response.ArticleResponse
import com.benidict.data.model.response.ContentResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getAllArticleByName(
        @Query("q") title : String,
        @Query("apiKey") apiKey: String): Single<Response<ContentResponse<ArticleResponse>>>

}