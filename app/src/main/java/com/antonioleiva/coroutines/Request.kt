package com.antonioleiva.coroutines

import kotlinx.coroutines.delay

class Request {

    suspend fun run(type: String): List<String> {
        delay((type.count() * 1000).toLong())
        return (1..type.count()).map { "$type $it" }
    }

}