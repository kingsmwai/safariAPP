package com.example.safariapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class FragSharingA : Fragment() {
     //set up a tag for our Communicator interface
    private lateinit var communicator: Communicator
    lateinit var editText: EditText
    lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_sharing_a, container, false)
        editText = view.findViewById(R.id.editText)
        button = view.findViewById(R.id.click)

        //intialize the interface
        communicator = activity as Communicator

        button.setOnClickListener {
            communicator.passData(editText.text.toString())
            Log.d("data","data from fragment is " + editText.text.toString())
        }


        return view


    }


}