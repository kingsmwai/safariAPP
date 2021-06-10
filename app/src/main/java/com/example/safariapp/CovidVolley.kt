package com.example.safariapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_covid_volley.*
import org.json.JSONException

class CovidVolley : AppCompatActivity() {

    private var mExampleAdapter: CovidVolleyAdapter? = null
    private var mExampleList: ArrayList<CovidVolleyModel>? = null
    private var mRequestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_volley)

        //give our recyclerview a view group
        covidRecycler.layoutManager = LinearLayoutManager(this)
        //set a fixed item size
        covidRecycler.setHasFixedSize(true)

        //make an instance of the ArrayList class
        mExampleList = ArrayList()
        //create an instance of volley
        mRequestQueue = Volley.newRequestQueue(this)

       //check if network is connected : we run the networking process
        if(isNetworkConnected()){
            fetchDetails()
        } else {
            val errorDialog = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            errorDialog.setTitleText("Loading ...") //Processing your request
            errorDialog.setCancelable(true)
            errorDialog.show()
        }


    }

    private fun fetchDetails() {
        val loadingDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        loadingDialog.setTitleText("Loading ...") //Processing your request
        loadingDialog.setCancelable(true)
        loadingDialog.setCanceledOnTouchOutside(false)
        loadingDialog.show()

        //have a variable defining out networking URL
        val url: String = "https://api.covid19api.com/summary"
        //is to use volley to get details in JSON
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                loadingDialog.dismiss()
                try {
                    val jsonArray = response.getJSONArray("Countries")
                    Log.d("array", "response is $jsonArray")
                    for (i in 0 until jsonArray.length()) {
                        val hit = jsonArray.getJSONObject(i)
                        val id = hit.getString("ID")
                        val countryName = hit.getString("Country")
                        val countryNewCases = hit.getInt("NewConfirmed")
                        val countryTotalCases = hit.getInt("TotalConfirmed")
                        val countryNewRec = hit.getInt("NewRecovered")
                        val countryTotalRec = hit.getInt("TotalRecovered")
                        val countryNewDeath = hit.getInt("NewDeaths")
                        val countryTotalDeath = hit.getInt("TotalDeaths")
                        val infoDate = hit.getString("Date")
                        mExampleList!!.add(
                            CovidVolleyModel(
                                id,
                                countryName,
                                countryNewCases,
                                countryTotalCases,
                                countryNewRec,
                                countryTotalRec,
                                countryNewDeath,
                                countryTotalDeath,
                                infoDate
                            )
                        )
                    }
                    //adding model data to adapter
                    mExampleAdapter = mExampleList?.let { CovidVolleyAdapter(this@CovidVolley, it) }
                    covidRecycler!!.adapter = mExampleAdapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener {
                    error -> error.printStackTrace()
                    Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            })
        mRequestQueue!!.add(request)
    }

    //checking if network is connected
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}