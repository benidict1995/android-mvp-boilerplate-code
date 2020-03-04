package com.benidict.android_mvp_boilerplate.common.rx

import com.benidict.android_mvp_boilerplate.common.mvp.MvpFragment
import com.benidict.android_mvp_boilerplate.common.mvp.MvpPresenter
import com.benidict.android_mvp_boilerplate.common.mvp.MvpView
import com.benidict.android_mvp_boilerplate.common.rx.event.RxFragmentEvent
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject


abstract class RxMvpFragment<V : MvpView, P : MvpPresenter<V>> : MvpFragment<V, P>(),
        LifecycleScopeProvider<RxFragmentEvent>{


    /* AutoDispose */
    private val lifecycleEvents: BehaviorSubject<RxFragmentEvent> = BehaviorSubject.create()

    private val CORRESPONDING_EVENTS =
         CorrespondingEventsFunction<RxFragmentEvent> { event: RxFragmentEvent? ->
            when (event) {
                RxFragmentEvent.ATTACH ->  RxFragmentEvent.DETACH
                RxFragmentEvent.CREATE ->  RxFragmentEvent.DESTROY
                RxFragmentEvent.CREATE_VIEW -> RxFragmentEvent.DESTROY_VIEW
                RxFragmentEvent.START -> RxFragmentEvent.STOP
                RxFragmentEvent.RESUME ->  RxFragmentEvent.PAUSE
                RxFragmentEvent.PAUSE ->  RxFragmentEvent.STOP
                RxFragmentEvent.STOP ->  RxFragmentEvent.DESTROY_VIEW
                RxFragmentEvent.DESTROY_VIEW ->  RxFragmentEvent.DESTROY
                RxFragmentEvent.DESTROY ->  RxFragmentEvent.DETACH
                else -> throw LifecycleEndedException("Cannot bind to Fragment lifecycle after detach.")
            }
        }

    override fun lifecycle(): Observable<RxFragmentEvent>? = lifecycleEvents.hide()


    override fun correspondingEvents(): CorrespondingEventsFunction<RxFragmentEvent>? = CORRESPONDING_EVENTS

    override fun peekLifecycle(): RxFragmentEvent? = lifecycleEvents.value




}
