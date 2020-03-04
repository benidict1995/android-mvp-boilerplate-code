package com.benidict.android_mvp_boilerplate.common.base

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.benidict.android_mvp_boilerplate.common.base.callback.ActivityCallBack
import com.benidict.android_mvp_boilerplate.common.rx.RxMvpFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<V : BaseFragmentView, P : BasePresenter<V>> : RxMvpFragment<V,P>(), BaseFragmentView,
ActivityCallBack{

    override fun preSetUpDependencies() {
        AndroidSupportInjection.inject(this)
    }

    override fun preSetUpViews(view: View) {
        getBaseActivity().setCallBack(this)
    }


    fun getBaseActivity() : BaseActivity<*,*> = activity as BaseActivity<*,*>


    override fun onBackPressed() {
        getBaseActivity().hideKeyboard()
        getNavController().popBackStack()
    }

    fun getNavController() : NavController = NavHostFragment.findNavController(this)

}