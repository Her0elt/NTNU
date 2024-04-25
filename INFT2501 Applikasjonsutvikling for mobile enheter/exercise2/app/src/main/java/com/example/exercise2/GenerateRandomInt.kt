package com.example.exercise2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class GenerateRandomInt : Activity(){
    private var upperBound = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_random_int)
        upperBound = intent.getIntExtra("upperBound", upperBound)

    }

    fun generateRandomInt(v: View?) {
        val intent = Intent()
        for (i in 0..10) {
            intent.putExtra("randomInt1$i", (0..upperBound).random())
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}