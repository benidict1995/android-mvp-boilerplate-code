package com.benidict.android_mvp_boilerplate.common.mvp

interface MvpPresenter<V : MvpView>{

    fun attachView(view: V)

    fun detachView()

    fun isViewAttached(): Boolean

    fun getView(): V

    fun checkViewAttached()

}