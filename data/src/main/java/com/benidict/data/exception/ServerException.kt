package com.benidict.data.exception

import java.lang.Exception

class ServerException(message: String?, cause: Throwable?) : Exception(message, cause)