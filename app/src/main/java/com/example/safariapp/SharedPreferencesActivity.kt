package com.example.safariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shared_preferences.*

class SharedPreferencesActivity : AppCompatActivity() {
    private val preferenceHelper : IPreferenceHelper by lazy { PreferenceManager(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        //displaying data to textview
        textView.text = " User name -> ${preferenceHelper.getUserName()} \n User email -> ${preferenceHelper.getUserEmail()} "

        //picking and setting user input to my shared pref.
        button.setOnClickListener {
            //using methods in my pref, manager to set values to the keys
            //stored values to the shared pref
            preferenceHelper.setUserName(edituser.text.toString())
            preferenceHelper.setUserEmail(editemail.text.toString())
           //displaying data to textview
            textView.text = " User name -> ${preferenceHelper.getUserName()} \n User email -> ${preferenceHelper.getUserEmail()} "

        }



    }
}