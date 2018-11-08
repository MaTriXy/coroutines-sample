@file:Suppress("unused")

package com.antonioleiva.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

//// ---- runBlocking ------

fun runBlockingTest() {
    val millis = measureTimeMillis {
        runBlocking {
            delay(2000)
            println(" --Coroutine finished-- ")
        }
    }

    println(" --$millis millis-- ")
}

//// -----------------------


/// -------- launch --------

fun runLaunchTest() {

    val millis = measureTimeMillis {
        GlobalScope.launch {
            delay(2000)
            println(" --Coroutine finished-- ")
        }
    }

    println(" --$millis millis-- ")
}


//// -----------------------

/// ----- suspendable ------

suspend fun sleep(millis: Long) = suspendCoroutine<Unit> { continuation ->
    Thread.sleep(millis)
    continuation.resume(Unit)
}

fun runSuspendFunTest() {
    val millis = measureTimeMillis {
        runBlocking {
            sleep(2000)
            println(" --Coroutine finished-- ")
        }
    }

    println(" --$millis millis-- ")
}


//// -----------------------


/// --------- async --------

fun runAsyncTest() = runBlocking {

    val millis = measureTimeMillis {

        val res1: Deferred<String> = async {
            delay(3000)
            println(" --Coroutine 1 finished-- ")
            "a"
        }

        val res2: Deferred<String> = async {
            delay(2000)
            println(" --Coroutine 2 finished-- ")
            "b"
        }

        val result = res1.await() + res2.await()
        println(" --Parent coroutine finished-- ")
    }

    println(" --$millis millis-- ")
}

fun runLazyAsyncTest() = runBlocking {

    val millis = measureTimeMillis {

        val res1: Deferred<String> = async(start = CoroutineStart.LAZY) {
            delay(3000)
            println(" --Coroutine 1 finished-- ")
            "a"
        }

        val res2: Deferred<String> = async(start = CoroutineStart.LAZY) {
            delay(2000)
            println(" --Coroutine 2 finished-- ")
            "b"
        }

        val result = res1.await() + res2.await()
        println(" --Parent coroutine finished-- ")
    }

    println(" --$millis millis-- ")
}

//// -----------------------


/// --------- jobs ---------

fun cancelCoroutine() = runBlocking {

    val millis = measureTimeMillis {
        val job: Job = launch {
            delay(2000)
            println(" --Coroutine finished-- ")
        }

        job.cancel()
    }

    println(" --$millis millis-- ")
}


fun waitForCoroutine() = runBlocking {

    val millis = measureTimeMillis {
        val job = launch {
            delay(2000)
            println(" --Coroutine finished-- ")
        }

        job.join()
    }

    println(" --$millis millis-- ")
}

//// -----------------------