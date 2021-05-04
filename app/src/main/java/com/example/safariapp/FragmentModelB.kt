package com.example.safariapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText


class FragmentModelB : Fragment() {
   //tag out share view model class
    lateinit var model: SharedViewModel
    lateinit var textData: TextInputEditText
    lateinit var btnShares: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_b, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view referencing
        textData = view.findViewById(R.id.editTextFragB)
        btnShares = view.findViewById(R.id.btnShare)

        //using share view model to share data
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        //set on click listener to button
        btnShares.setOnClickListener {
            //capture users input
            val userInput = textData.text.toString().trim()
            model.sendMessage(userInput)
        }


    }


}