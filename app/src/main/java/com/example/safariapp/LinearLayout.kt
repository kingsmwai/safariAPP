package com.example.safariapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class LinearLayout : AppCompatActivity() {
    //intializing ur widget
    lateinit var textName: TextView
    lateinit var btnRelative: TextView
    lateinit var btnConstraint: Button
    lateinit var btnToast: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linearlayout)

        //reference the buttons to give it an action
        btnRelative = findViewById(R.id.relative)
        btnConstraint = findViewById(R.id.constraint)
        btnToast = findViewById(R.id.toastNotify)

        //set on click listener
        btnToast.setOnClickListener(View.OnClickListener {
            //This is how u do a toast
            Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show()

        })

        btnRelative.setOnClickListener(View.OnClickListener {
            val intentRelative  = Intent(this,RelativeLayout::class.java)
            startActivity(intentRelative)
            Log.d("check","button click not working" + intentRelative)
        })

        btnConstraint.setOnClickListener(View.OnClickListener {
            val intentConstraint  = Intent(this,ConstraintLayout::class.java)
            startActivity(intentConstraint)
        })

    }
}