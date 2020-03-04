package com.benidict.android_mvp_boilerplate.common.injection.modules


import android.app.Application
import android.content.Context
import com.benidict.android_mvp_boilerplate.common.utils.helper.SessionManager
import com.benidict.domain.util.RxBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

        @Provides
        @Singleton
        fun provideContext(application: Application): Context = application


        @Provides
        @Singleton
        fun provideRxBus(): RxBus {
            return RxBus.create()
        }

        @Provides
        @Singleton
        fun provideSessionManager(rxBus: RxBus): SessionManager {
            return SessionManager(rxBus)
        }

}
