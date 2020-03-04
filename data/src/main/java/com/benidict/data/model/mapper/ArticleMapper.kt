package com.benidict.data.model.mapper

import com.benidict.data.model.response.ArticleResponse
import com.benidict.domain.model.Article
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.internal.operators.observable.ObservableFromIterable

class ArticleMapper{


    companion object{


        fun mapArticleResponse(response: List<ArticleResponse>): Single<List<Article>>
            = Observable.fromIterable(response)
            .map {
                Article(
                    author = it.author,
                    title = it.title,
                    description = it.description,
                    url = it.url,
                    urlToImage = it.urlToImage,
                    publishedAt = it.publishedAt
                )
            }.toList()

    }

}