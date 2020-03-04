package com.benidict.domain.gateway

import io.reactivex.Single

interface ApiKeyGateway{

    fun loadApiKey(): Single<String>

}