package com.example.codemaster.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codemaster.R
import me.onebone.toolbar.CollapsingToolbarScope


val font = FontFamily(Font(R.font.acme_regular))
@Composable
fun TopAppBar() {
    TopAppBar(
        backgroundColor = Color.White,
        contentPadding = PaddingValues(20.dp),
        elevation = 0.dp
    ) {

        Text(
            text = "CodeMaster",
            style = TextStyle(color = Color.Black),
            fontSize = 34.sp,
            modifier = Modifier.padding(start = 20.dp, top = 15.dp),
            textAlign = TextAlign.Start,
            fontFamily =  font
        )
    }
}