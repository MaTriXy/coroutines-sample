package com.antonioleiva.coroutines

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    private val connectionChecker = ConnectionChecker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()

        setContentView(R.layout.activity_main)

        connect.setOnClickListener {
            progress.visibility = View.VISIBLE

            launch {
                val success = connectionChecker.checkConnection()
                progress.visibility = View.GONE

                if (success) {
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java))
                } else {
                    toast("Couldn't connect")
                }
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}