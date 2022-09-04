package com.example.codemaster.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen(
    viewModel: InputListViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(179, 200, 255, 255))
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Task",
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier.height(6.dp))
            TextField(
                    value = viewModel.username,
                    onValueChange = {
                        viewModel.onEvent(InputListEvent.OnUsernameChange(it))
                    },
                    placeholder = {
                        Text(text = "Title")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                modifier = Modifier,
                onClick = {
                    viewModel.onEvent(InputListEvent.onSaveClick)
                },
                colors = ButtonDefaults.buttonColors(Color(205, 220, 57, 255))


            ) {
                Text(text = "Click")
            }
        }
    }
}