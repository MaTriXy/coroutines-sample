package com.antonioleiva.coroutines

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private val connectionChecker = ConnectionChecker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connect.setOnClickListener {
            progress.visibility = View.VISIBLE

            connectionChecker.checkConnection { success ->

                progress.visibility = View.GONE

                if (success) {
                    startActivity(Intent(this, DetailActivity::class.java))
                } else {
                    toast("Couldn't connect")
                }
            }
        }
    }
}