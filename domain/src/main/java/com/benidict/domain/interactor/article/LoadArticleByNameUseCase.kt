package com.benidict.domain.interactor.article

import com.benidict.domain.base.BaseUseCase
import com.benidict.domain.gateway.ApiKeyGateway
import com.benidict.domain.gateway.ArticleGateway
import com.benidict.domain.model.Article
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class LoadArticleByNameUseCase

@Inject
constructor(
    @Named("execution") executionThread: Scheduler,
    @Named("post_execution") postExecutionThread: Scheduler,
    private val articleGateway: ArticleGateway,
    private val apiKeyGateway: ApiKeyGateway
): BaseUseCase(
    executionThread,
    postExecutionThread
){


    fun execute(title: String): Single<List<Article>>
        = apiKeyGateway.loadApiKey()
        .map { it }
        .flatMap { apiKey -> articleGateway.loadArticleByName(title, apiKey) }
        .compose(applySingleSchedulers())

}