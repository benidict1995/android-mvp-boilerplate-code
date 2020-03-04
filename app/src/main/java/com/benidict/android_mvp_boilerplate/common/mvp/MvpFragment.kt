package com.benidict.android_mvp_boilerplate.common.mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment

abstract class MvpFragment<V : MvpView, P : MvpPresenter<V>> : Fragment(){

    lateinit var mPresenter: P

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    @NonNull
    protected abstract fun getMvpPresenter(): P

    @NonNull
    protected abstract fun getMvpView(): V

    /**
     * Setup 3rd party libraries such as Dagger
     */
    protected abstract fun preSetUpDependencies()

    protected abstract fun setupDependencies()


    /**
     * Setup 3rd party libraries such as ButterKnife or DataBinding
     * @param view View
     */
    protected abstract fun preSetUpViews(@NonNull view: View)

    protected abstract fun setupViews()

    protected abstract fun loadContent()

    override fun onAttach(context: Context) {
        preSetUpDependencies()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutRes(), container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()

        mPresenter = getMvpPresenter()
        mPresenter.attachView(getMvpView())

        preSetUpViews(view)
        setupViews()
        loadContent()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.detachView()
    }
}