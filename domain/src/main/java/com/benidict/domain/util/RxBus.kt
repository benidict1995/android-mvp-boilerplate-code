package com.benidict.domain.util

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

class RxBus{

    private val bus: Relay<Any> = BehaviorRelay.create()

    companion object Instance{
        private val instance: RxBus?= null
        fun create(): RxBus = instance ?: RxBus()
    }

    fun publish(any: Any) = bus.accept(any)


    fun <T> subscribe(type: Class<T>): Observable<T> = bus.ofType(type)


}