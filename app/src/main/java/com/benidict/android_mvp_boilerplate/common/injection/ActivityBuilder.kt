package com.benidict.android_mvp_boilerplate.common.injection


import com.benidict.android_mvp_boilerplate.example.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity


}