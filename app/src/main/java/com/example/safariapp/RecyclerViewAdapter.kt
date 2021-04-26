package com.example.safariapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycle_item.view.*

class RecyclerViewAdapter(private val context: Context,private val itemList: List<RecyclerViewModel>)  : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
           val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item,parent,false)
           return RecyclerViewHolder(inflater)

    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerViewAdapter.RecyclerViewHolder, position: Int) {
        val items  = itemList[position]

        //set the data
        holder.imageView.setImageResource(items.imageResource)
        holder.textTitle.text = items.title

        //functions to the views
        holder.imageView.setOnClickListener {
            Toast.makeText(context,"recycler click",Toast.LENGTH_LONG).show()

        }
    }



    //creating view holder class to enable me to ref the views inside the recycled item
    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.imageItem
        val textTitle: TextView = itemView.textItem
    }
}