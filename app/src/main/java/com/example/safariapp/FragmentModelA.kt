package com.example.safariapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class FragmentModelA : Fragment() {
    //ref to the sharedviewModel class
    //ref to the textView
    lateinit var  model: SharedViewModel
    lateinit var textSample: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_a, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        //view ref
        textSample = view.findViewById(R.id.textFragA)

        //using model to reference data stored within the mutableLiveData class
        model.message.observe(viewLifecycleOwner, Observer {
            //set the text shared to my layout
            textSample.text = it
        })
    }


}