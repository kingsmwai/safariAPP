package com.example.safariapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_background_logic.*

class BackgroundLogic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_logic)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

//            view referencing  and also picking value currently on textview
            val textValue: String = textView4.text as String
            //convert value above to be integer
            val intValue: Int = Integer.valueOf(textValue)

            //making a ref, to my business logic
            //concept of oop , to create a new object of the myWorker class
            val myWorker = MyWorker()
            val newValue = myWorker.doubleTheValue(intValue)
            //set the new value of textview when floating button is clicked
            textView4.text = newValue.toString()

//
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
        }
    }
}