package com.ady.magicmirror.util

import retrofit2.Call

fun <T> Call<T>.enqueue(): String {
    return someConstant
}
