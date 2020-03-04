package com.benidict.domain.gateway

import com.benidict.domain.model.Article
import io.reactivex.Single

interface ArticleGateway{

    fun loadArticleByName(title: String, apiKey: String): Single<List<Article>>

}