package com.benidict.data.exception

import java.lang.Exception


class ClientException(message: String?, cause: Throwable?) : Exception(message, cause)