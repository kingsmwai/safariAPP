package com.example.safariapp

interface IPreferenceHelper {
    //define abstract get and set methods
    fun getUserName()
    fun getUserEmail()
    fun setUserName(username: String)
    fun setUserEmail(useremail: String)
    fun clearPrefs()
}