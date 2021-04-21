package com.example.safariapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class ListViewActivity : AppCompatActivity() {
    //tag to the list view widget
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        //view identification
        listView = findViewById(R.id.listViewDefault)

        //reference to our items
        val items:Array<String> = resources.getStringArray(R.array.technology)
        //ref. to the arrayadapter class which will enable us to set up our items on a list display
        val listAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items)

        //map items to listview
        listView.adapter = listAdapter

        //set a click listener on the items
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            //control flows for item clicks
            if (position == 0){
               //intent for phone
//                val phone = "0798501657"
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:" + "0798501657")
                startActivity(dialIntent)
            } else if (position == 1){
               //intent for email
                val addresses = "josephbill00@gmail.com"
                val subject = "works"
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.setData(Uri.parse("mailto:")) // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, addresses)
                intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                if (intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(intent)
                }

            } else if (position == 2){
               //intent for web
                val url = "http://josephbill.github.io"
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(url))
                startActivity(i)
            } else if (position == 3){
               //intent for
                Toast.makeText(this,"List view clicks work",Toast.LENGTH_LONG).show()
            } else if (position == 4){
                //custom list view
                val intentCustom = Intent(this,CustomListView::class.java)
                startActivity(intentCustom)
            }



        }



    }
}