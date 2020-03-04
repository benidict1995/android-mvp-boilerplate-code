package com.benidict.android_mvp_boilerplate.common.rx

import com.benidict.android_mvp_boilerplate.common.mvp.MvpView
import com.benidict.android_mvp_boilerplate.common.mvp.base.MvpBasePresenter
import com.benidict.android_mvp_boilerplate.common.rx.event.RxPresenterEvent
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject

abstract class RxMvpPresenter<V : MvpView> : MvpBasePresenter<V>(),
    LifecycleScopeProvider<RxPresenterEvent> {

    /* AutoDispose */
    private val lifecycleEvents: BehaviorSubject<RxPresenterEvent> = BehaviorSubject.create()

    private val CORRESPONDING_EVENTS =
        CorrespondingEventsFunction<RxPresenterEvent> { event: RxPresenterEvent? ->
            when (event) {
                RxPresenterEvent.ATTACH -> RxPresenterEvent.DETACH
                else -> throw LifecycleEndedException("Cannot bind to Presenter lifecycle after detach.")
            }
        }

    override fun lifecycle(): Observable<RxPresenterEvent>  = lifecycleEvents.hide()

    override fun correspondingEvents(): CorrespondingEventsFunction<RxPresenterEvent> = CORRESPONDING_EVENTS

    override fun peekLifecycle(): RxPresenterEvent? = lifecycleEvents.value

    override fun attachView(view: V) {
        lifecycleEvents.onNext(RxPresenterEvent.ATTACH)
        super.attachView(view)
    }

    override fun detachView() {
        lifecycleEvents.onNext(RxPresenterEvent.DETACH)
        super.detachView()
    }

}
