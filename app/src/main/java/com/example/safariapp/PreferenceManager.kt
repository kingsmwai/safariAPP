package com.example.safariapp

import android.content.Context
import android.content.SharedPreferences

open class PreferenceManager constructor(context: Context) : IPreferenceHelper {
    //private constant variable that can access the shared preferences file
    private val PREFS_NAME = "SharedPreferencesDemo"
    private var preferences: SharedPreferences

    //declaring an init block where i will create the instance of the sharedprefs
    init {
        preferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
    }

    override fun getUserName() {
        preferences[USER_NAME] ?: ""
    }

    override fun getUserEmail() {
        preferences[USER_EMAIL] ?: ""
    }

    override fun setUserName(username: String) {
        preferences[USER_NAME] = username
    }

    override fun setUserEmail(useremail: String) {
        preferences[USER_EMAIL] = useremail
    }

    //method for clearing our data set in the preferences
    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }

    //create a companion object
    companion object {
        //define variables to rep. the values to be stored in the shared prefs
        const val USER_NAME = "Joseph"
        const val USER_EMAIL = "josephbill00@gmail.com"
    }

    //the writing process
    /**
     * SharedPreferences extension function, to listen the edit() and apply() fun calls
     * on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    private operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    //the reading process
    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    private inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }



}