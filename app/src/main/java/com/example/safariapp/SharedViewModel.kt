package com.example.safariapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    //define the MutableLiveData object : to hold data
    val message = MutableLiveData<String>()

    //creating function to send message
    fun sendMessage(text: String){
        message.value = text
    }
}