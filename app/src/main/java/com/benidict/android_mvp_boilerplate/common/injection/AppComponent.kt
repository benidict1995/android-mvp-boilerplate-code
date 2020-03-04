package com.benidict.android_mvp_boilerplate.common.injection

import android.app.Application
import android.content.Context
import com.benidict.android_mvp_boilerplate.BaseApplication
import com.benidict.android_mvp_boilerplate.common.injection.modules.AppModule
import com.benidict.android_mvp_boilerplate.common.injection.modules.DataModule
import com.benidict.android_mvp_boilerplate.common.injection.modules.DomainModule
import com.benidict.android_mvp_boilerplate.common.injection.modules.GatewayModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        GatewayModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication>{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun packageName(@Named("packageName") packageName: String): Builder

        @BindsInstance
        fun executionThread(@Named("execution") scheduler: Scheduler): Builder

        @BindsInstance
        fun postExecutionThread(@Named("post_execution") scheduler: Scheduler): Builder

        @BindsInstance
        fun getContext(@Named("context") context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApplication)
}

