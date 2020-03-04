package com.benidict.data.webservice.operator

import com.benidict.data.exception.ServerException
import com.benidict.data.exception.ServiceErrorResp
import com.benidict.data.exception.ServiceException
import com.google.gson.Gson
import io.reactivex.ObservableOperator
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response

class ServiceOperator<T> constructor(private val gson:Gson) : ObservableOperator<T, Response<T>> {

    override fun apply(observer: Observer<in T>): Observer<in Response<T>> {
        return object : Observer<Response<T>>{
            override fun onComplete() {
                observer.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onNext(response: Response<T>) {
                val code = response.code()
                try {
                    if (code == 400 || code == 401 || code == 403 || code == 404 || code == 422 || code == 409 || code == 500 || code == 502) {
                        val jsonError = response.errorBody()?.string()
                        val errorResp: ServiceErrorResp =
                            gson.fromJson(jsonError, ServiceErrorResp::class.java)


                        observer.onError(ServiceException(errorResp.message()))

                        return

                    }

                    response.body()?.let { observer.onNext(it) }
                } catch (e: Exception) {
                    val message = "Sorry, something went wrong. " +
                            "We’re working on it and we’ll get it fixed as soon as we can."
                    observer.onError(ServerException(message, e))
                }
            }

            override fun onError(e: Throwable) {
             }
        }
    }
}