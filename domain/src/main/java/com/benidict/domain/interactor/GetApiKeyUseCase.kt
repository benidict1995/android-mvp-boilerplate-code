package com.benidict.domain.interactor

import com.benidict.domain.base.BaseUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class GetApiKeyUseCase

@Inject
constructor(
    @Named("execution") executionThread: Scheduler,
    @Named("post_execution") postExecutionThread: Scheduler
    ): BaseUseCase(executionThread, postExecutionThread){

    fun execute(): Single<String>
        = Single.just("0e923629cc2c44119b31aa981420af37")
        .compose(applySingleSchedulers())

}