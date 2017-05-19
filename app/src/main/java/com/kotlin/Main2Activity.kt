package com.kotlin

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*


import com.example.admin.myapplication.R
import kotlinx.android.synthetic.main.content_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val tvMsg = findViewById(R.id.tv_msg) as TextView
        Log.i("info","======11111="+tvMsg.text)
        tvMsg.text="111asdasdasdasd"
        Log.i("info","======11111="+tvMsg.text)
        tvMsg.textColors

        tv_msg.setText("lallalalallalalallalalallala")
        tv_msg.text = "sdfsdfsdfsdfsdfsdfsdf"
        Log.i("info","========================tv_msg="+tv_msg.text)



        main()
    }

    fun main(){
        Log.i("info","======Hello Kotlin======")
    }


}
