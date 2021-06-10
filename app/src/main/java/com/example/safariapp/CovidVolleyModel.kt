package com.example.safariapp

data class CovidVolleyModel(val id: String, val countryName_m: String, val countryNewCases_m: Int, val countryTotalCases_m: Int,
                       val countryNewRec_m: Int, val countryTotalRec_m: Int, val countryNewDeath_m: Int, val countryTotalDeath_m: Int, val date_m: String)  {
}