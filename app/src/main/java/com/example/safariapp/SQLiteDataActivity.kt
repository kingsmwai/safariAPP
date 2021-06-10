package com.example.safariapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_s_q_lite_data.*

class SQLiteDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_q_lite_data)


        btnDelete.setOnClickListener {

            deleteData()
        }

        btnUpdate.setOnClickListener {

            updateData()
        }

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

    fun deleteData(){
         val dialogBuilder = AlertDialog.Builder(this)
         val inflater = this.layoutInflater
          //attach our custom view to the pop up
         val dialogView = inflater.inflate(R.layout.delete_dialog,null)
         dialogBuilder.setView(dialogView)

         //capture edit text
         val delete_Id  = dialogView.findViewById<EditText>(R.id.deleteId)
         //customize our pop up , title and a message
         dialogBuilder.setTitle("Delete Data")
         dialogBuilder.setMessage("Enter id to delete data")

        //set up our button actions
        dialogBuilder.setPositiveButton("Delete Data",DialogInterface.OnClickListener { dialog, which ->

//            set what happens when the positive button is clicked
            //capture user input
            val inputId = delete_Id.text.toString()
            //create instance of the database handler class
            val databaseHandler = DatabaseHandler(this)
            //validate that the inputId variable actually has data in it
            if (inputId.trim() != ""){
                //here we will use our delete method
                val status = databaseHandler.deleteData(sqlListModel(Integer.valueOf(inputId),"",""))
                if (status > -1){
                    Toast.makeText(applicationContext,"Record successfully deleted",Toast.LENGTH_LONG).show()

                }
            } else {
                Toast.makeText(applicationContext,"Id field must not be empty",Toast.LENGTH_LONG).show()
            }
            
        })


        dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->

//            set what happens when negative btn is clicked
            Toast.makeText(applicationContext,"Process cancelled",Toast.LENGTH_LONG).show()
            //dismiss pop up
            dialog.dismiss()

        })

        dialogBuilder.setNeutralButton("Help",DialogInterface.OnClickListener { dialog, which ->

            Toast.makeText(applicationContext,"Simply enter record id to delete from SQLite",Toast.LENGTH_LONG).show()
        })

        //to create and show
        val b  = dialogBuilder.create()
        b.show()



    }

    fun updateData(){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        //attach our custom view to the pop up
        val dialogView = inflater.inflate(R.layout.update_dialog,null)
        dialogBuilder.setView(dialogView)

        //ref to the views
        val update_id = dialogView.findViewById<EditText>(R.id.updateId)
        val update_name = dialogView.findViewById<EditText>(R.id.updateName)
        val update_email = dialogView.findViewById<EditText>(R.id.updateEmail)

        //customize the pop up
        dialogBuilder.setTitle("Update Data")
        dialogBuilder.setMessage("Enter an id , to update specific record")

        dialogBuilder.setPositiveButton("Update Data",DialogInterface.OnClickListener { dialog, which ->
              val updateID = update_id.text.toString()
              val updateNAME = update_name.text.toString()
              val updateEMAIL = update_email.text.toString()

              //validate
             if (updateID.trim() != "" && updateNAME.trim() != "" && updateEMAIL.trim() != ""){
                   //update record
                   //instance of our database handler
                    val databaseHandler  = DatabaseHandler(this)
                    val status = databaseHandler.updateData(sqlListModel(Integer.valueOf(updateID),updateNAME,updateEMAIL))

                    if(status > -1){
                        Toast.makeText(applicationContext,"Update successful",Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(applicationContext,"Update failed",Toast.LENGTH_LONG).show()

                    }
             } else {
                 Toast.makeText(applicationContext,"Update fields cannot be empty",Toast.LENGTH_LONG).show()
             }

        })

        dialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->

//            set what happens when negative btn is clicked
            Toast.makeText(applicationContext,"Process cancelled",Toast.LENGTH_LONG).show()
            //dismiss pop up
            dialog.dismiss()

        })

        dialogBuilder.setNeutralButton("Help",DialogInterface.OnClickListener { dialog, which ->

            Toast.makeText(applicationContext,"Simply enter record id to update from SQLite",Toast.LENGTH_LONG).show()
        })

        //to create and show
        val b  = dialogBuilder.create()
        b.show()

    }


}