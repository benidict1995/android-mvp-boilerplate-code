package com.benidict.android_mvp_boilerplate.common.injection.modules

import com.benidict.data.gateway.ApiKeyDataGateway
import com.benidict.data.gateway.ArticleDataGateway
import com.benidict.domain.gateway.ApiKeyGateway
import com.benidict.domain.gateway.ArticleGateway
import dagger.Module
import dagger.Provides

@Module
class GatewayModule{

    @Provides
    fun provideArticleGateway(articleDataGateway: ArticleDataGateway): ArticleGateway = articleDataGateway

    @Provides
    fun provideApiGateway(apiKeyDataGateway: ApiKeyDataGateway): ApiKeyGateway = apiKeyDataGateway

}