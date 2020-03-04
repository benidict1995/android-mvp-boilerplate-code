package com.benidict.android_mvp_boilerplate

import android.app.Application
import com.benidict.android_mvp_boilerplate.common.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BaseApplication : Application() , HasAndroidInjector{


    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }


    private fun initDagger(){
        DaggerAppComponent
            .builder()
            .application(this)
            .packageName("")
            .executionThread(Schedulers.io())
            .postExecutionThread(AndroidSchedulers.mainThread())
            .getContext(this)
            .build()
            .inject(this)
    }


    override fun androidInjector(): AndroidInjector<Any> = activityInjector
}