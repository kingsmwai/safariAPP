package com.example.safariapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.covid_volley_item.view.*
import org.w3c.dom.Text

class CovidVolleyAdapter(private val context: Context,private val itemlists: List<CovidVolleyModel>)  : RecyclerView.Adapter<CovidVolleyAdapter.CovidViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CovidVolleyAdapter.CovidViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.covid_volley_item,parent,false)
        return CovidVolleyAdapter.CovidViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CovidVolleyAdapter.CovidViewHolder, position: Int) {
           val items  = itemlists[position]
           holder.countryName.text = "Country: " + items.countryName_m
           holder.countryNewCases.text = "New Cases"  + items.countryNewCases_m.toString()
           holder.countryTotalCases.text = "Total Cases " + items.countryTotalCases_m.toString()
           holder.countryNewRec.text = "New Rec: " + items.countryNewRec_m.toString()
           holder.countryTotalRec.text = "Total Rec: " + items.countryTotalRec_m.toString()
           holder.countryNewDeath.text = "New Death " + items.countryNewDeath_m.toString()
           holder.countryTotalDeath.text = "Total Death " + items.countryTotalDeath_m.toString()
           holder.infoDate.text = items.date_m
    }

    override fun getItemCount() = itemlists.size

    class CovidViewHolder(view: View) : RecyclerView.ViewHolder(view){
        //kotlin extensions to reference the ids
        val countryName : TextView = view.coronaCountry
        val countryNewCases : TextView = view.coronaNewCases
        val countryTotalCases: TextView = view.coronaTotalCases
        val countryNewRec : TextView = view.coronaNewRec
        val countryTotalRec : TextView = view.coronaTotalRec
        val countryNewDeath : TextView = view.coronaNewDeath
        val countryTotalDeath  : TextView = view.coronaTotalDeath
        val infoDate : TextView = view.infoDate

    }
}