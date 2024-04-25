package com.example.exercise4.image

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun ImageList(
    images: List<DImage>,
    pickImage: (Int) -> Unit
) {
    LazyColumn {
        items(images.size) { index ->
            Button(onClick = { pickImage(index) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = images[index].name)
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}