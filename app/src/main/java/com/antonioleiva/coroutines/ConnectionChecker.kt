package com.antonioleiva.coroutines

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ConnectionChecker {

    fun checkConnection(callback: (Boolean) -> Unit) {

        doAsync {
            Thread.sleep(2000)
            uiThread {
                callback(true)
            }
        }
    }

}