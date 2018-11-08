package com.antonioleiva.coroutines

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val request = Request()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        progress.visibility = View.VISIBLE

        val startTime = System.currentTimeMillis()

        request.run("cat") { cats ->

            request.run("elephant") { elephants ->

                val result = cats + elephants

                val totalSecs = (System.currentTimeMillis() - startTime) / 1000.0

                counter.text = "${result.count()} items - $totalSecs seconds"

                progress.visibility = View.GONE
            }

        }
    }
}
