package com.example.codemaster.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnackbarDemo(
    snackbarVisibleState : Boolean
) {
    if (snackbarVisibleState) {
        Snackbar(
            action = {
                Button(onClick = {}) {
                    Text("MyAction")
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "This is a snackbar!")
        }
    }
}