package com.benidict.android_mvp_boilerplate.common.utils.helper

import com.benidict.domain.common.helper.BusHelper
import com.benidict.domain.util.RxBus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AppBusHelper

@Inject

constructor(private val bus: RxBus): BusHelper {


    override fun publish(any: Any) = bus.publish(any)

    override fun <T> subscribe(type: Class<T>): Observable<T> = bus.subscribe(type).observeOn(AndroidSchedulers.mainThread())

    override fun <T> subscribeOnMainThread(type: Class<T>): Observable<T> =
        bus.subscribe(type).debounce(250, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())



}