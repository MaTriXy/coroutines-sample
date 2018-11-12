package com.antonioleiva.coroutines

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class DetailActivity : CoroutineActivity() {

    private val request = Request()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        launch {

            progress.visibility = View.VISIBLE

            lateinit var result: List<String>

            val timeMillis = measureTimeMillis {
                val cats = async { request.run("cat") }
                val elephants = async { request.run("elephant") }
                result = cats.await() + elephants.await()
            }

            counter.text = "${result.count()} items - ${timeMillis / 1000.0} seconds"

            progress.visibility = View.GONE
        }

    }
}
