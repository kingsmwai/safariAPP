package com.example.safariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class LifeCycleActivity : AppCompatActivity() {
    var lifecycle : String  = "lifecycle activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

       Log.d(lifecycle,"onCreate Lifecycle running")
    }

    override fun onStart() {
        super.onStart()
        Log.d(lifecycle,"onstart is running")
    }

    override fun onResume() {
        super.onResume()
        Log.d(lifecycle,"onResume is running")

    }

    override fun onPause() {
        super.onPause()
        Log.d(lifecycle,"onPause is running")

    }

    override fun onStop() {
        super.onStop()
        Log.d(lifecycle,"onstop is running")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(lifecycle,"onDestroy is running")

    }


}