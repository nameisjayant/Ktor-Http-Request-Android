package com.codingwithjks.ktorhttprequest.data.util

import com.codingwithjks.ktorhttprequest.data.Post

sealed class ApiState{
    object Loading : ApiState()
    object Empty : ApiState()
    class Success(val response: List<Post>) : ApiState()
    class Failure(val error:Throwable) : ApiState()
}
