package com.benidict.android_mvp_boilerplate.common.mvp.base

import com.benidict.android_mvp_boilerplate.common.mvp.MvpPresenter
import com.benidict.android_mvp_boilerplate.common.mvp.MvpView

abstract class MvpBasePresenter<T : MvpView> : MvpPresenter<T> {

    private var mView: T?= null


    override fun attachView(view: T)  { mView = view }

    override fun detachView() { mView = null }

    override fun isViewAttached(): Boolean  = mView != null

    override fun getView(): T  = mView?:mView!!

    @SuppressWarnings("unused")
    override fun checkViewAttached() {
        if (!isViewAttached()) throw ViewNotAttachedException()
    }

    @SuppressWarnings("unused")
    class ViewNotAttachedException : RuntimeException(
        "Please call Presenter.attachView(MvpView) before" +
                " requesting data to the Presenter"
    )
}