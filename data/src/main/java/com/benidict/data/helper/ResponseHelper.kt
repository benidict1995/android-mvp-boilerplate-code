package com.benidict.data.helper

import com.benidict.data.webservice.operator.ServiceOperator
import com.benidict.data.webservice.operator.SingleServiceOperator
import com.google.gson.Gson
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import retrofit2.Response
import javax.inject.Inject

class ResponseHelper @Inject constructor(private val gson: Gson){


    fun <T> handleResp(): ObservableTransformer<Response<T>, T>
        = ObservableTransformer { upstream ->
        upstream.lift(ServiceOperator(gson))
    }


    fun <T> handleSingleResp(): SingleTransformer<Response<T>, T>{
        return SingleTransformer { upstream ->
            upstream.lift(SingleServiceOperator(gson))
        }
    }


}