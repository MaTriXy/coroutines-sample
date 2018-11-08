package com.antonioleiva.coroutines

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Request {

    fun run(type: String, callback: (List<String>) -> Unit) {
        doAsync {
            Thread.sleep((type.count() * 1000).toLong())
            val res = (1..type.count()).map { "$type $it" }

            uiThread {
                callback(res)
            }
        }
    }

}