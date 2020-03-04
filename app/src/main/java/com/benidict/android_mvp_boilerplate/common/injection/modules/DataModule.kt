package com.benidict.android_mvp_boilerplate.common.injection.modules

import com.benidict.android_mvp_boilerplate.common.utils.DateConstant
import com.benidict.data.helper.ResponseHelper
import com.benidict.data.webservice.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class DataModule{

        @Provides
        @Singleton
        fun provideGson(): Gson
                = GsonBuilder()
            .setDateFormat(DateConstant.DATE_FORMAT_1)
            .create()


        @Provides
        @Singleton
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor
                = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)


        @Provides
        @Singleton
        fun provideResponseHelper(gson: Gson): ResponseHelper = ResponseHelper(gson)


//    @Provides
//    @Singleton
//    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()


        @Provides
        @Singleton
        fun provideOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient
                = OkHttpClient.Builder()
            .apply {
                addInterceptor(loggingInterceptor)
                connectTimeout(30L, TimeUnit.SECONDS)
                readTimeout(60L, TimeUnit.SECONDS)
                writeTimeout(60L, TimeUnit.SECONDS)
            }.build()


        @Provides
        @Singleton
        fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit
                = Retrofit.Builder()
            .client(client)
            .baseUrl("http://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        @Provides
        @Singleton
        fun provideGetGoService(retrofit: Retrofit): ApiService
                = retrofit.create(ApiService::class.java)

}