package com.example.safariapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class IntentSharingA : AppCompatActivity() {
    //intialization
    lateinit var imageLogo: ImageView
    lateinit var textView: TextView
    lateinit var editText: EditText
    lateinit var buttonClick: Button

    //string
    var textSample1:String = ""
    var textEdit: String = ""
    //int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_sharing)

        //view identification
        imageLogo = findViewById(R.id.imageLogo)
        textView = findViewById(R.id.textSample)
        editText = findViewById(R.id.edit_text)
        buttonClick = findViewById(R.id.btnClick)

        //fetch the text from textView
        textSample1 = textView.text as String



        //set onclick listener where i will also share data
        buttonClick.setOnClickListener {
            //fetch the text from Edit Text
            textEdit = editText.text.toString()
            Log.d("shareData", "Text from textView " + textSample1 + "Text from Edit Text " + textEdit)
            val intentSharingA = Intent(this@IntentSharingA,IntentSharingB::class.java)
            //intent to share text
            intentSharingA.putExtra("textFromTV",textSample1)
            intentSharingA.putExtra("textFromET", textEdit)
            intentSharingA.putExtra("digit","0")
            //intent to share image
            intentSharingA.putExtra("image",R.drawable.applogo)
            startActivity(intentSharingA)
        }




    }
}