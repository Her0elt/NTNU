package com.example.exercise4.image

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@Composable
fun ImageShowCase(
    images: List<DImage>,
    currentImage: Int,
    nextImage : () -> Unit,
    prevImage : () -> Unit
) {
    Column(modifier = Modifier.fillMaxHeight(), Arrangement.SpaceBetween) {
        ImageCard(images[currentImage])
        Spacer(modifier = Modifier.weight(1.0f))
        Row {
            Button(
                onClick = prevImage, modifier = Modifier
                    .weight(1.0f)
                    .fillMaxWidth()
            ) {
                Text(text = "Last")
            }
            Button(
                onClick = nextImage, modifier = Modifier
                    .weight(1.0f)
                    .fillMaxWidth()
            ) {
                Text(text = "Next")
            }
        }
    }
}