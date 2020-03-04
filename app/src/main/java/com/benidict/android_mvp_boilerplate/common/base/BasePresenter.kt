package com.benidict.android_mvp_boilerplate.common.base

import com.benidict.android_mvp_boilerplate.common.mvp.MvpView
import com.benidict.android_mvp_boilerplate.common.rx.RxMvpPresenter

abstract class BasePresenter<V: MvpView> : RxMvpPresenter<V>()