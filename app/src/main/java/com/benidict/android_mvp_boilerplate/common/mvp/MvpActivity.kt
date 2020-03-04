package com.benidict.android_mvp_boilerplate.common.mvp

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

/**
 * Mvp Activity
 * @param V interface class that will represent as View
 * @param P class that will represent as Presenter
**/
abstract class MvpActivity <V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(){

    lateinit var mPresenter : P

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    @NonNull
    protected abstract fun getMvpView(): V

    @NonNull
    protected abstract fun getMvpPresenter(): P

    /**
     * Setup 3rd party libraries such as Dagger
     */
    protected abstract fun preSetUpDependencies()

    protected abstract fun setupDependencies()

    protected abstract fun preSetupViews()



    protected abstract fun setupViews()

    protected abstract fun loadContent()

    override fun onCreate(savedInstanceState: Bundle?) {
        preSetUpDependencies()
        super.onCreate(savedInstanceState)
        setupDependencies()
        mPresenter = getMvpPresenter()
        mPresenter.attachView(getMvpView())

        setContentView(getLayoutRes())
        preSetupViews()
        setupViews()
        loadContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}