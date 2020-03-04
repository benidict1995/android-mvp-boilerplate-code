package com.benidict.domain.base

import io.reactivex.*
import javax.inject.Inject

open class BaseUseCase @Inject constructor(
    var executionThread: Scheduler,
    var postExecutionThread: Scheduler
) {

     fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream: Observable<T> ->
            upstream.subscribeOn(executionThread)
                .observeOn(postExecutionThread)
        }
    }

     fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { upstream: Single<T> ->
            upstream.subscribeOn(executionThread)
                .observeOn(postExecutionThread)
        }
    }

     fun applyCompletableSchedulers(): CompletableTransformer {
        return CompletableTransformer { upstream: Completable ->
            upstream.subscribeOn(executionThread)
                .observeOn(postExecutionThread)
        }
    }

}
