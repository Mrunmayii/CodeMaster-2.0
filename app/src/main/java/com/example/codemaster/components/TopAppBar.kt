package com.example.codemaster.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codemaster.R
import com.example.codemaster.ui.codeforces_screen.CodeforcesViewModel
import com.example.codemaster.utils.UiEvent


val font = FontFamily(Font(R.font.acme_regular))
@Composable
fun TopAppBar(
    title : String,
    onNavigate: (UiEvent.Navigate) -> Unit,
    topBarViewModel: TopBarViewModel = hiltViewModel()
) {
//    TopAppBar(
//        backgroundColor = Color.White,
//        contentPadding = PaddingValues(20.dp),
//        elevation = 0.dp
//    ) {
//
//        Text(
//            text = "CodeMaster",
//            style = TextStyle(color = Color.Black),
//            fontSize = 34.sp,
//            modifier = Modifier.padding(start = 20.dp, top = 15.dp),
//            textAlign = TextAlign.Start,
//            fontFamily =  font
//        )
//    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = TextStyle(color = Color.Black),
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                textAlign = TextAlign.Start,
                fontFamily = font
            )

            IconButton(
                modifier = Modifier.padding(end = 10.dp),
                onClick = {
                        topBarViewModel.onEvent(TopBarUiEvent.onSettingsClick)
                }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "null",
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))

    LaunchedEffect(key1 = true) {
        topBarViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }

        }
    }
}