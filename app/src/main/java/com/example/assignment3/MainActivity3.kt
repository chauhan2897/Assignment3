package com.example.assignment3
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        Handler().postDelayed(
            {
                var sqlitedatabase: SQLiteDatabase
                sqlitedatabase = openOrCreateDatabase("Company", 0, null)
                sqlitedatabase.execSQL("create table if not exists tail(name varchar(25),mobile bigint(10))")
                Toast.makeText(applicationContext,"table created",Toast.LENGTH_SHORT).show()
                var name = toString()
                var sql = "select *from tail "
                var cursor: Cursor = sqlitedatabase.rawQuery(sql, null)
                var cursor1 = cursor.count

                if (cursor1 > 0) {
                    cursor.moveToFirst()
                    var name=cursor.getString(0)
                    var view:TextView=findViewById(R.id.view)
                    view.setText(name+"/n")
                    Toast.makeText(applicationContext, "Login successfull", Toast.LENGTH_SHORT)
                        .show()
                    var intent = Intent(applicationContext, MainActivity2::class.java)
                    startActivity(intent)
                }
                else if(cursor1 == 0){
                    var intent=Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }, SPLASH_TIME_OUT)

    }
}