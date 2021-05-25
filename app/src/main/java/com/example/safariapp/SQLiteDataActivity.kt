package com.example.safariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_s_q_lite_data.*

class SQLiteDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_q_lite_data)
    }

    //saving data
    fun saveData(view: View){
        //capture user data
        val id = editId.text.toString()
        val name = editName.text.toString()
        val email = editEmail.text.toString()
        //instance of our database handler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //validation
        if (id.trim() != "" && name.trim() != "" && email.trim() != ""){
            //if its not equal to null , write to my sqlite
            val status = databaseHandler.addUsers(sqlListModel(Integer.valueOf(id),name,email))
            //if data is added
            if (status > -1){
                Toast.makeText(applicationContext,"Record saved",Toast.LENGTH_LONG).show()
                //user experience , clear inputs
                editId.text?.clear()
                editName.text?.clear()
                editEmail.text?.clear()
            }

        } else {
            //if its null, notify the user
            Toast.makeText(applicationContext,"fields cannot be empty",Toast.LENGTH_LONG).show()
        }

    }

    //reading of data
    fun viewData(view: View){
        //define instance of our database Handler
        val databaseHandler = DatabaseHandler(this)
        //
        val viewdata: List<sqlListModel> = databaseHandler.readData()
        //defining array variables to store each records details
        val empArrayId = Array<String>(viewdata.size){"0"}
        val empArrayName = Array<String>(viewdata.size){"null"}
        val empArrayEmail = Array<String>(viewdata.size){"null"}
        //looping our records in sqlite db and save them to our variables above
        var index = 0
        for(e in viewdata){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index++
        }

        //create the details for our adapter
        val myadpater = sqlListAdapter(this,empArrayId,empArrayName,empArrayEmail)
        //set the adpater to ur listview
        listItems.adapter = myadpater
    }


}