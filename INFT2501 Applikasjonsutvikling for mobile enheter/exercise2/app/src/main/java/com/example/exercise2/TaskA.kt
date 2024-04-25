package com.example.exercise2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class TaskA : AppCompatActivity() {
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK-> {
                    if(result.data != null){
                        val generatedNumber = result.data!!.getIntExtra("randomInt1", 5)
                        Toast.makeText(this, "Generert tall: $generatedNumber", Toast.LENGTH_LONG)
                            .show()
                        val textView = findViewById<View>(R.id.task_a_textview) as TextView
                        textView.text = "Generert tall: $generatedNumber"
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_a)
    }

    fun onClickGenerateRandomInt(v: View?) {
        startForResult.launch(Intent(this, GenerateRandomInt::class.java))
    }

}