package com.example.assignment3

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text: EditText = findViewById(R.id.edittext1)
        var text1: EditText = findViewById(R.id.edittext2)
        var button: Button = findViewById(R.id.button1)
        var sqlitedatabase: SQLiteDatabase
        //0 is for exxcess modifiers i.e-private public etc
        sqlitedatabase = openOrCreateDatabase("Company", 0, null)
        Toast.makeText(applicationContext, "database created", Toast.LENGTH_SHORT).show()

        sqlitedatabase.execSQL("create table if not exists tail(name varchar(25),mobile bigint(10))")
        Toast.makeText(applicationContext, "table created", Toast.LENGTH_SHORT).show()
        button.setOnClickListener {
            var name = text.text.toString()
            var mobile = text1.text.toString()
            var intent = intent.setData(Uri.parse("tel:" + mobile))

            if (name.isEmpty() || mobile.isEmpty()) {
                Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT)
                    .show()
            } else if (mobile.length < 10) {
                Toast.makeText(applicationContext, "Password length must be 10 char", Toast.LENGTH_SHORT).show()
            } else {
                sqlitedatabase.execSQL("insert into tail values('" + name + "','" + mobile + "')")
                Toast.makeText(applicationContext, "Data saved", Toast.LENGTH_SHORT).show()
                var intent= Intent(applicationContext,MainActivity2::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}