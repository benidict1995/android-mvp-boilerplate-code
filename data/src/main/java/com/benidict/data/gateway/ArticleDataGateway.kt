package com.benidict.data.gateway

import com.benidict.data.model.mapper.ArticleMapper
import com.benidict.data.webservice.client.ArticleClient
import com.benidict.domain.gateway.ArticleGateway
import com.benidict.domain.model.Article
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleDataGateway

@Inject
constructor(
    private val articleClient: ArticleClient
): ArticleGateway{

    override fun loadArticleByName(title: String, apiKey: String): Single<List<Article>>
        = articleClient.loadArticleByName(title, apiKey)
        .flatMap {
            ArticleMapper.mapArticleResponse(it.articles!!)
        }
}