package com.example.safariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_sharing_b.*

class IntentSharingB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_sharing_b)

        //pick image shared
        //declare an instance of the bundle class
        val bundle: Bundle? = intent.extras
        val imagePicked: Int = bundle!!.getInt("image")
        Log.d("shareData" ,"image name " + imagePicked.toString())
        //set the image to the ImageView
        imageLogo.setImageResource(imagePicked)

        //picking text
        val sharedTextTV: String? = intent.getStringExtra("textFromTV")
        val sharedTextET: String? = intent.getStringExtra("textFromET")
        val sharedTextDigit: String? = intent.getStringExtra("digit")

        //set the text to containers
        textSample.append("text from tv is: " + sharedTextTV + " " + " text from ET is: " + sharedTextET + " " + " digit data: " + sharedTextDigit)


    }
}