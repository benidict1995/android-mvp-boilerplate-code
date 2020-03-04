package com.benidict.domain.common.helper

import io.reactivex.Observable

interface BusHelper{

    fun publish(any: Any)

    fun <T> subscribe(type: Class<T>): Observable<T>

    fun <T> subscribeOnMainThread(type: Class<T>): Observable<T>

}