package com.soyak.kotlin_helper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adappter =
        GenericAdapter<MyModel>(R.layout.row_model)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list: ArrayList<MyModel> = arrayListOf(
            MyModel(),
            MyModel(),
            MyModel()
        )
        setContentView(R.layout.activity_main)
        rv.adapter = adappter
        adappter.addItems(list)
    }
}
