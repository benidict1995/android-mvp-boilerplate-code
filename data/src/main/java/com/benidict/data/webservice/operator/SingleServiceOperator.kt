package com.benidict.data.webservice.operator

import com.benidict.data.exception.*
import com.benidict.data.webservice.response.ServiceErrorPayment
import com.google.gson.Gson
import io.reactivex.SingleObserver
import io.reactivex.SingleOperator
import io.reactivex.disposables.Disposable
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SingleServiceOperator<T> constructor(private val gson: Gson) : SingleOperator<T, Response<T>> {

    override fun apply(observer: SingleObserver<in T>): SingleObserver<in Response<T>> {
        return object : SingleObserver<Response<T>> {
            override fun onSuccess(response: Response<T>) {
                val code = response.code()

                try {
                    if (code == 400 || code == 401 || code == 403 || code == 404 || code == 422 || code == 409 || code == 500 || code == 502) {


                        val jsonError = response.errorBody()?.string()
                        val errorResp: ServiceErrorResp =
                            gson.fromJson(jsonError, ServiceErrorResp::class.java)

                        observer.onError(ServiceException(errorResp.message()))

                        return


                    }else if(code == 412){

                        val jsonError = response.errorBody()?.string()
                        val errorResp: ServiceErrorPayment = gson.fromJson(jsonError, ServiceErrorPayment::class.java)

                        observer.onError(ServiceException(errorResp.message()))
                        return
                    }

                    response.body()?.let { observer.onSuccess(it) }
                } catch (e: Exception) {
                    val message = "Sorry, something went wrong. " +
                            "We’re working on it and we’ll get it fixed as soon as we can."
                    observer.onError(ServerException(message, e))
                }
            }

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onError(e: Throwable) {
                if (e is UnknownHostException
                    || e is NoNetworkException) {
                    val message = "Uh oh! Looks like you lost your internet connection. " +
                            "Please check your network settings and try again later."
                    observer.onError(ClientException(message, e))
                } else if (e is SocketException
                    || e is SocketTimeoutException) {
                    val message = "Server is busy. Please try again later."
                    observer.onError(ClientException(message, e))
                }
            }
        }
    }
}