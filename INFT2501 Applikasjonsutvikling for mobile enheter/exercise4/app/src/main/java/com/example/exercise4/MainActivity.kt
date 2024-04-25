package com.example.exercise4

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.exercise4.image.DImage
import com.example.exercise4.image.ImageList
import com.example.exercise4.image.ImageShowCase
import com.example.exercise4.image.ImageState
import com.example.exercise4.ui.theme.Exercise4Theme

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    private val imageState by viewModels<ImageState>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exercise4Theme {
                Surface(color = MaterialTheme.colors.background) {
                    Box {
                        Column() {
                            Row(
                                modifier = Modifier
                                    .weight(1.0f)
                                    .fillMaxWidth()
                            ) {
                               ImageList(
                                   images = imageState.images,
                                   pickImage = imageState::pickImage
                               )
                            }

                            Row(
                                modifier = Modifier
                                    .weight(1.0f)
                                    .fillMaxWidth()
                            ) {
                                ImageShowCase(
                                    images = imageState.images,
                                    currentImage = imageState.image,
                                    nextImage = imageState::nextImage,
                                    prevImage = imageState::prevImage
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}