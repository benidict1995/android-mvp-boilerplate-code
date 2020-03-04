package com.benidict.android_mvp_boilerplate.common.base

import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import com.benidict.android_mvp_boilerplate.common.base.callback.ActivityCallBack
import com.benidict.android_mvp_boilerplate.common.rx.RxMvpActivity
import dagger.android.AndroidInjection

abstract class BaseActivity<V: BaseActivityView, P : BasePresenter<V>> : RxMvpActivity<V,P>(){

    private lateinit var callBack: ActivityCallBack

    companion object {
        private var activityStarted = false
    }

    fun setCallBack(callBack: ActivityCallBack){
        this.callBack = callBack
    }

    fun hideKeyboard() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    override fun preSetUpDependencies() {
        AndroidInjection.inject(this)
    }

    override fun preSetupViews() {
        if (activityStarted
            && intent != null
            && intent.flags and Intent.FLAG_ACTIVITY_REORDER_TO_FRONT != 0) {
            finish()
            return
        }

        activityStarted = true
    }

    override fun onBackPressed() {
        callBack.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }
}