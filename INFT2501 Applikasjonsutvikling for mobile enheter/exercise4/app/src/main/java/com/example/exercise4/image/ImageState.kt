package com.example.exercise4.image

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.exercise4.R

class ImageState : ViewModel() {

    var images = mutableListOf(
        DImage("dog", R.drawable.dog),
        DImage("bat",  R.drawable.bat),
        DImage("cat",  R.drawable.cat)
    )

    var image by  mutableStateOf(0)

    fun nextImage(){
        image = (++image) % images.size
    }
    fun prevImage(){
        image = (--image) % images.size
    }
    fun pickImage(index: Int){
        image = index
    }




}
