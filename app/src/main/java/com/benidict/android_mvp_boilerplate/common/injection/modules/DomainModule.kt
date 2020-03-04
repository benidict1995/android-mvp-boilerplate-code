package com.benidict.android_mvp_boilerplate.common.injection.modules

import com.benidict.android_mvp_boilerplate.common.utils.helper.AppBusHelper
import com.benidict.domain.common.helper.BusHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule{

        @Provides
        @Singleton
        fun provideBusHelper(appBusHelper: AppBusHelper): BusHelper = appBusHelper

}