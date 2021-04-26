package com.example.safariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        //setting my list items for my recycler view
        val listRecycler = generateDummyList()
        //ref the recycler view widget and set the adapter to it
        recyclerView.adapter = RecyclerViewAdapter(this,listRecycler)
        //give our recycler items a view group
        recyclerView.layoutManager = LinearLayoutManager(this)
        //set a fixed item size
        recyclerView.setHasFixedSize(true)

    }

    private fun generateDummyList(): List<RecyclerViewModel> {

        //arraylist to add data
        val listItems  = ArrayList<RecyclerViewModel>()

        listItems.add(
            RecyclerViewModel(
                R.drawable.applogo,
                "First Item"
            )
        )

        listItems.add(
            RecyclerViewModel(
                R.drawable.logo,
                "Second Item"
            )
        )

        listItems.add(
            RecyclerViewModel(
                R.drawable.linear,
                "Third Item"
            )
        )


        return listItems
    }
}


