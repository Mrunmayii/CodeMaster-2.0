package com.example.codemaster.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.security.MessageDigest

@Composable
fun ErrorDialog(message : String) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        Column(modifier = Modifier.fillMaxSize()) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("Done")
                    }
                }
            )
        }

    }
}