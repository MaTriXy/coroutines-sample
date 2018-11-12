package com.antonioleiva.coroutines

import kotlinx.coroutines.delay

class ConnectionChecker {

    suspend fun checkConnection(): Boolean {
        delay(2000)
        return true
    }

}