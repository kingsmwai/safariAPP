package com.example.safariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast

class CustomListView : AppCompatActivity() {
    val titles =  arrayOf("Joseph","Kate","Brian","Angie","Dexter")
    val image:Array<Int> = arrayOf(R.drawable.applogo,R.drawable.ic_launcher_background,R.drawable.linear,R.drawable.relative,R.drawable.logo)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view)

        //creating ref to adapter
        val customListViewAdapter = CustomListViewAdapter(this,titles,image)
        //set the listview to use the adapter
        val listView: ListView = findViewById(R.id.customList)
        listView.adapter = customListViewAdapter

        //setting item click listener
        listView.setOnItemClickListener { parent, view, position, id ->

            if (position == 0){
                Toast.makeText(applicationContext,"item 1",Toast.LENGTH_LONG).show()

            } else if(position == 1){
                Toast.makeText(applicationContext,"item 2",Toast.LENGTH_LONG).show()
            }


        }


    }
}