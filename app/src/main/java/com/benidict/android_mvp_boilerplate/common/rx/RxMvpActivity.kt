package com.benidict.android_mvp_boilerplate.common.rx

import android.os.Bundle
import com.benidict.android_mvp_boilerplate.common.mvp.MvpActivity
import com.benidict.android_mvp_boilerplate.common.mvp.MvpPresenter
import com.benidict.android_mvp_boilerplate.common.mvp.MvpView
import com.benidict.android_mvp_boilerplate.common.rx.event.RxActivityEvent
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class RxMvpActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>(),
        LifecycleScopeProvider<RxActivityEvent>{


    /* AutoDispose */
    private val lifecycleEvents: BehaviorSubject<RxActivityEvent> = BehaviorSubject.create()

    private val CORRESPONDING_EVENTS =
         CorrespondingEventsFunction<RxActivityEvent> { event: RxActivityEvent? ->
            when (event) {
                RxActivityEvent.CREATE ->  RxActivityEvent.DESTROY
                RxActivityEvent.START ->   RxActivityEvent.STOP
                RxActivityEvent.RESUME ->  RxActivityEvent.PAUSE
                RxActivityEvent.PAUSE ->  RxActivityEvent.STOP
                RxActivityEvent.STOP ->  RxActivityEvent.DESTROY
                else -> throw LifecycleEndedException("Cannot bind to Activity lifecycle after destroy.")
            }
        }

    override fun lifecycle(): Observable<RxActivityEvent?>? = lifecycleEvents.hide()


    override fun correspondingEvents():  CorrespondingEventsFunction<RxActivityEvent> = CORRESPONDING_EVENTS

    override fun peekLifecycle(): RxActivityEvent? = lifecycleEvents.value



    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycleEvents.onNext(RxActivityEvent.CREATE)
        super.onCreate(savedInstanceState)
    }


    override fun onStart() {
        lifecycleEvents.onNext(RxActivityEvent.START)
        super.onStart()
    }

    override fun onResume() {
        lifecycleEvents.onNext(RxActivityEvent.RESUME)
        super.onResume()
    }

    override fun onPause() {
        lifecycleEvents.onNext(RxActivityEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycleEvents.onNext(RxActivityEvent.STOP)
        super.onStop()
    }

    override fun onDestroy() {
        lifecycleEvents.onNext(RxActivityEvent.DESTROY)
        super.onDestroy()
    }
}


