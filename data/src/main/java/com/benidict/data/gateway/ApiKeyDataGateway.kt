package com.benidict.data.gateway

import com.benidict.domain.base.BaseUseCase
import com.benidict.domain.gateway.ApiKeyGateway
import io.reactivex.Single
import javax.inject.Inject

class ApiKeyDataGateway


@Inject
constructor(): ApiKeyGateway{

    override fun loadApiKey(): Single<String>
        = Single.just("0e923629cc2c44119b31aa981420af37")
}