package com.benidict.data.exception

import com.google.gson.annotations.SerializedName

data class ServiceErrorResp(
    @SerializedName("loginStatus") val status:String,
    @SerializedName("errors") val errors: List<ServiceErrorResp>,
    @SerializedName("error_credentials") val msg: String?= null) {



    fun message(): String {

        if (msg.isNullOrEmpty()){
            var message = ""

            for (error in errors) {
                message += error.msg
                if (errors.indexOf(error) < errors.size - 1) {
                    message += "\n"
                }
            }

            //Comment this code once the code not working. this is for test purposes
            if (message.isEmpty()) {
                message = "Something went wrong."
            }
            return message
        }

        return msg.toString()
    }
}