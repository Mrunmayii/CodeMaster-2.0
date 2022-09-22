package com.example.codemaster.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codemaster.data.source.local.enitity.Username
import com.example.codemaster.ui.contest_screen.ContestCard

@Composable
fun Platform(
    username: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(235, 248, 255, 255))
            .padding(30.dp)
    ){
        Row(
            modifier = Modifier.padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                text = "Select Platform Details",
                textAlign = TextAlign.Center
            )
        }
        Text(text = "codechef", modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp))
        UserTextField("  Username")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "codeforces", modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp))
        UserTextField("  Username")
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "leetcode", modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp))
        UserTextField("  Username")
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTextField(
    data : String
) {
    var value by remember {
        mutableStateOf("")
    }
    TextField(
        value = value,
        onValueChange = {
            value = it
        },
        placeholder = {
            Text(
                text = data,
                color = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = false,
        isError = false,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            containerColor = Color.White,
        ),
        shape = ShapeDefaults.ExtraSmall,
        textStyle = TextStyle(lineHeight = 120.sp),
    )
}

