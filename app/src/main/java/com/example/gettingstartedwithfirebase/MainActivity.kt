package com.example.gettingstartedwithfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var submit:Button
    lateinit var textView:TextView

    val database:FirebaseDatabase=FirebaseDatabase.getInstance()
    val reference:DatabaseReference=database.reference.child("USERS")
    val reference2:DatabaseReference=database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText=findViewById(R.id.editText)
        submit=findViewById(R.id.submit)
        textView=findViewById(R.id.textView)

        reference2.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value:String = snapshot.child("USERS").child("UserName").value as String
                textView.text=value

            }

            override fun onCancelled(error: DatabaseError) {


            }
        })

        submit.setOnClickListener {
            val userName=editText.text.toString()
            reference.child("UserName").setValue(userName)
        }
    }
}