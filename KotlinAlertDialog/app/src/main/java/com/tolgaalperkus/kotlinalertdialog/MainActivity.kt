package com.tolgaalperkus.kotlinalertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Context
            //1.Activity Context -> this
            //2.App Context -> applicationContext


        Toast.makeText(this@MainActivity,"hoşgeldin",Toast.LENGTH_LONG).show()


        /*
        activity_main_alert_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@MainActivity, "test2", Toast.LENGTH_LONG).show()
            }
        })
         */
    }

    fun save (view : View){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Save")
        alert.setMessage("Are You Sure")
        alert.setPositiveButton("Yes"){dialog,which ->
            //Onclick Listener
            Toast.makeText(applicationContext, "kaydedildi", Toast.LENGTH_SHORT).show()
        }
        alert.setNegativeButton("No") {dialog,which ->
            //Onclick Listener
            Toast.makeText(applicationContext, "Kaydedilmedi", Toast.LENGTH_SHORT).show()
        }
        alert.show()

    }
}