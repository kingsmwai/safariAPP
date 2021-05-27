package com.example.safariapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
          private val DATABASE_VERSION = 1
          private val DATABASE_NAME = "UserDatabase"
          private val TABLE_USERS = "UserTable"
          private val KEY_ID = "id"
          private val KEY_NAME = "name"
          private val KEY_EMAIL = "email"
    }

    //creating SQLite Database
    override fun onCreate(db: SQLiteDatabase?) {
        //defining our query
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")")
        //execute our query
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    //notifying the db incase of a change
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
              db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS)
              onCreate(db)
            }

    //method to create records
    fun addUsers(sqlListModel: sqlListModel): Long{
        val db = this.writableDatabase
        //defining and placing our content
        val contentValues = ContentValues()
        //put data to respective fields
        contentValues.put(KEY_ID, sqlListModel.userId)
        contentValues.put(KEY_NAME, sqlListModel.userName)
        contentValues.put(KEY_EMAIL, sqlListModel.userEmail)
        //insert this row to table in database
        val success = db.insert(TABLE_USERS,null,contentValues)
        //close the database connection
        db.close()
        //return output of method
        return success

    }

    //method to read data
    fun readData(): List<sqlListModel>{
        //get resizeable array for our data
        val userArray: ArrayList<sqlListModel> = ArrayList<sqlListModel>()
        //defining our select query
        val selectQuery = "SELECT  * FROM $TABLE_USERS"
        //define the sqlite method
        val db = this.readableDatabase
        //reading our data and saving it to our arraylist
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        //iterating our records in db and storing them to our model
        var userId: Int
        var userName: String
        var userEmail: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex("id"))
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userEmail = cursor.getString(cursor.getColumnIndex("email"))
                val emp= sqlListModel(userId = userId, userName = userName, userEmail = userEmail)
                userArray.add(emp)
            } while (cursor.moveToNext())
        }

        return userArray
    }

    //update method
    fun updateData(sqlListModel: sqlListModel) : Int{
        //process in db
        val db  = this.writableDatabase
        //content values
        val contentValues = ContentValues()
        //put the details inside the respective colums
        contentValues.put(KEY_ID, sqlListModel.userId)
        contentValues.put(KEY_NAME, sqlListModel.userName)
        contentValues.put(KEY_EMAIL, sqlListModel.userEmail)
        //updating the row
        val success = db.update(TABLE_USERS,contentValues,"id=" + sqlListModel.userId,null)
        //close the connection
        db.close()

        return success

    }

    //delete method
    fun deleteData(sqlListModel: sqlListModel) : Int{
        //process
        val db = this.writableDatabase
        //contentvalues
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, sqlListModel.userId)
        //delete process
        val success = db.delete(TABLE_USERS,"id=" + sqlListModel.userId,null)
        db.close()
        return success

    }


}