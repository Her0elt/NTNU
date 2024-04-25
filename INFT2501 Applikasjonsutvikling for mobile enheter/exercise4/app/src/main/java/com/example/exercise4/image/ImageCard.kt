package com.example.exercise4.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun ImageCard(image: DImage) {
        val painter = rememberImagePainter(
            data = image.image,
            builder = {
                crossfade(true)
            }
        )
        Box {
            Column ( Modifier.align(Alignment.Center)){
                Image(
                    painter = painter,
                    contentDescription = image.name,
                    modifier = Modifier.fillMaxSize(1f)
                )
            }
        }

}