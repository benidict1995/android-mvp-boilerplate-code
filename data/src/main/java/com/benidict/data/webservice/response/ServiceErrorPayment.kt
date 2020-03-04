package com.benidict.data.webservice.response

import com.google.gson.annotations.SerializedName

class ServiceErrorPayment (
    @SerializedName("code") val code : String,
    @SerializedName("data") val data : String,
    @SerializedName("errors") val errors : List<ServiceError>
){

    fun message(): String {
        var message = ""

        for (error in errors) {
            message += error.message
            if (errors.indexOf(error) < errors.size - 1) {
                message += "\n"
            }
        }

        if (message.isEmpty()) {
            message = "Something went wrong."
        }

        return message
    }
}