package com.antonioleiva.coroutines

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast

class MainActivity : CoroutineActivity() {

    private val connectionChecker = ConnectionChecker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
}