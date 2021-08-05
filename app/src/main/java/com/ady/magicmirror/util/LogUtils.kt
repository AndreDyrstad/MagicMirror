package com.ady.magicmirror.util

import android.util.Log

object LogUtils {

    fun debug(klass: Any, log: String) {
        Log.e(createTag(klass), log)
    }

    fun debug(klass: Any, log: String, t: Throwable) {
        Log.e(createTag(klass), log, t)
    }

    private fun createTag(klass: Any): String {
        return klass::class.java.simpleName
    }
}