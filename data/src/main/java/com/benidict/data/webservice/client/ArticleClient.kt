package com.benidict.data.webservice.client

import com.benidict.data.helper.ResponseHelper
import com.benidict.data.model.response.ArticleResponse
import com.benidict.data.model.response.ContentResponse
import com.benidict.data.webservice.ApiService
import io.reactivex.Single
import javax.inject.Inject

class ArticleClient

@Inject
constructor(
    private val apiService: ApiService,
    private val responseHelper: ResponseHelper
){

    fun loadArticleByName(title: String, apiKey: String): Single<ContentResponse<ArticleResponse>>
        = apiService.getAllArticleByName(title, apiKey)
        .compose(responseHelper.handleSingleResp())

}