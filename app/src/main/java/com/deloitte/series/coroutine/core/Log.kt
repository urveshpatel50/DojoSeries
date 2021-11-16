package com.deloitte.series.coroutine.core

import android.util.Log

object Log {

    private const val TAG = "PhotosApplication"
    private const val STACK_TRACE_INDEX = 4

    @JvmStatic
    fun debug(msg: String) {
        Log.d(TAG, getLogMessage(msg))
    }

    @JvmStatic
    fun info(msg: String) {
        Log.i(TAG, getLogMessage(msg))
    }

    @JvmStatic
    fun warning(msg: String) {
        Log.w(TAG, getLogMessage(msg))
    }

    @JvmStatic
    fun error(msg: String) {
        Log.e(TAG, getLogMessage(msg))
    }

    @JvmStatic
    fun error(throwable: Throwable) {
        Log.e(TAG, getLogMessage(throwable.toString()))
    }

    @JvmStatic
    fun error(msg: String, throwable: Throwable) {
        Log.e(TAG, getLogMessage(msg), throwable)
    }

    @JvmStatic
    fun verbose(msg: String) {
        Log.v(TAG, getLogMessage(msg))
    }

    private fun getLogMessage(msg: String): String {
        val ste = Thread.currentThread().stackTrace
        val logMsg = StringBuilder()

        if (ste.size > STACK_TRACE_INDEX && ste[STACK_TRACE_INDEX] != null) {
            logMsg.append("(")
                    .append(ste[STACK_TRACE_INDEX].fileName)
                    .append(":")
                    .append(ste[STACK_TRACE_INDEX].lineNumber)
                    .append(") ")
        }
        logMsg.append(msg)

        return logMsg.toString()
    }
}